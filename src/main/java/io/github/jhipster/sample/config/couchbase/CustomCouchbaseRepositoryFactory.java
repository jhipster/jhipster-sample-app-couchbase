package io.github.jhipster.sample.config.couchbase;

import java.lang.reflect.Method;
import java.util.Optional;
import org.springframework.data.couchbase.core.CouchbaseOperations;
import org.springframework.data.couchbase.repository.config.RepositoryOperationsMapping;
import org.springframework.data.couchbase.repository.query.CouchbaseQueryMethod;
import org.springframework.data.couchbase.repository.support.CouchbaseRepositoryFactory;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.repository.core.NamedQueries;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.QueryMethodEvaluationContextProvider;
import org.springframework.data.repository.query.RepositoryQuery;

public class CustomCouchbaseRepositoryFactory extends CouchbaseRepositoryFactory {

    private MappingContext mappingContext;
    private RepositoryOperationsMapping couchbaseOperationsMapping;

    /**
     * Create a new factory.
     *
     * @param couchbaseOperationsMapping the template for the underlying actions.
     */
    public CustomCouchbaseRepositoryFactory(RepositoryOperationsMapping couchbaseOperationsMapping) {
        super(couchbaseOperationsMapping);
        this.couchbaseOperationsMapping = couchbaseOperationsMapping;
        this.mappingContext = this.couchbaseOperationsMapping.getMappingContext();
    }

    @Override
    protected Optional<QueryLookupStrategy> getQueryLookupStrategy(
        QueryLookupStrategy.Key key,
        QueryMethodEvaluationContextProvider contextProvider
    ) {
        return Optional.of(new CustomCouchbaseQueryLookupStrategy(contextProvider));
    }

    private class CustomCouchbaseQueryLookupStrategy implements QueryLookupStrategy {

        private final QueryMethodEvaluationContextProvider evaluationContextProvider;

        public CustomCouchbaseQueryLookupStrategy(QueryMethodEvaluationContextProvider evaluationContextProvider) {
            this.evaluationContextProvider = evaluationContextProvider;
        }

        @Override
        public RepositoryQuery resolveQuery(
            final Method method,
            final RepositoryMetadata metadata,
            final ProjectionFactory factory,
            final NamedQueries namedQueries
        ) {
            final CouchbaseOperations couchbaseOperations = couchbaseOperationsMapping.resolve(
                metadata.getRepositoryInterface(),
                metadata.getDomainType()
            );

            CouchbaseQueryMethod queryMethod = new CouchbaseQueryMethod(method, metadata, factory, mappingContext);
            return new CustomCouchbaseRepositoryQuery(couchbaseOperations, queryMethod, namedQueries);
        }
    }
}
