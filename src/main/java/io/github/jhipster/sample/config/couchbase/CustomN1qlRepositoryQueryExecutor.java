package io.github.jhipster.sample.config.couchbase;

import com.couchbase.client.java.query.QueryScanConsistency;
import org.springframework.data.couchbase.core.CouchbaseOperations;
import org.springframework.data.couchbase.core.ExecutableFindByQueryOperation;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.repository.query.CouchbaseQueryMethod;
import org.springframework.data.couchbase.repository.query.ReactiveN1qlRepositoryQueryExecutor;
import org.springframework.data.couchbase.repository.query.StringN1qlQueryCreator;
import org.springframework.data.repository.core.NamedQueries;
import org.springframework.data.repository.query.ParameterAccessor;
import org.springframework.data.repository.query.ParametersParameterAccessor;
import org.springframework.data.repository.query.QueryMethodEvaluationContextProvider;
import org.springframework.data.repository.query.parser.PartTree;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class CustomN1qlRepositoryQueryExecutor {

    private final CouchbaseOperations operations;
    private final CouchbaseQueryMethod queryMethod;
    private final NamedQueries namedQueries;

    public CustomN1qlRepositoryQueryExecutor(
        final CouchbaseOperations operations,
        final CouchbaseQueryMethod queryMethod,
        final NamedQueries namedQueries
    ) {
        this.operations = operations;
        this.queryMethod = queryMethod;
        this.namedQueries = namedQueries;
    }

    /**
     * see also {@link ReactiveN1qlRepositoryQueryExecutor#execute(Object[] parameters) execute }
     *
     * @param parameters substitute values
     * @return with data
     */
    public Object execute(final Object[] parameters) {
        final Class<?> domainClass = queryMethod.getResultProcessor().getReturnedType().getDomainType();
        final ParameterAccessor accessor = new ParametersParameterAccessor(queryMethod.getParameters(), parameters);

        // this is identical to ReactiveN1qlRespositoryQueryExecutor,
        // except for the type of 'q', and the call to oneValue() vs one()
        Query query;
        ExecutableFindByQueryOperation.ExecutableFindByQuery q;
        if (queryMethod.hasN1qlAnnotation()) {
            query =
                new StringN1qlQueryCreator(
                    accessor,
                    queryMethod,
                    operations.getConverter(),
                    operations.getBucketName(),
                    new SpelExpressionParser(),
                    QueryMethodEvaluationContextProvider.DEFAULT,
                    namedQueries
                )
                    .createQuery();
        } else {
            final PartTree tree = new PartTree(queryMethod.getName(), domainClass);
            query = new CustomN1qlQueryCreator(operations, tree, accessor, queryMethod, operations.getConverter()).createQuery();
        }
        q =
            (ExecutableFindByQueryOperation.ExecutableFindByQuery) operations
                .findByQuery(domainClass)
                .consistentWith(buildQueryScanConsistency())
                .matching(query);
        if (queryMethod.isCountQuery()) {
            return q.count();
        } else if (queryMethod.isCollectionQuery()) {
            return q.all();
        } else {
            return q.oneValue();
        }
    }

    private QueryScanConsistency buildQueryScanConsistency() {
        QueryScanConsistency scanConsistency = QueryScanConsistency.NOT_BOUNDED;
        if (queryMethod.hasConsistencyAnnotation()) {
            scanConsistency = queryMethod.getConsistencyAnnotation().value();
        } else if (queryMethod.hasScanConsistencyAnnotation()) {
            scanConsistency = queryMethod.getScanConsistencyAnnotation().query();
        }
        return scanConsistency;
    }
}
