package io.github.jhipster.sample.config.couchbase;

import io.github.jhipster.sample.config.couchbase.CustomN1qlRepositoryQueryExecutor;
import org.springframework.data.couchbase.core.CouchbaseOperations;
import org.springframework.data.couchbase.repository.query.CouchbaseQueryMethod;
import org.springframework.data.couchbase.repository.query.CouchbaseRepositoryQuery;
import org.springframework.data.repository.core.NamedQueries;

public class CustomCouchbaseRepositoryQuery extends CouchbaseRepositoryQuery {

    private CouchbaseOperations couchbaseOperations;
    private CouchbaseQueryMethod queryMethod;
    private NamedQueries namedQueries;

    public CustomCouchbaseRepositoryQuery(CouchbaseOperations operations, CouchbaseQueryMethod queryMethod, NamedQueries namedQueries) {
        super(operations, queryMethod, namedQueries);
        this.couchbaseOperations = operations;
        this.queryMethod = queryMethod;
        this.namedQueries = namedQueries;
    }

    @Override
    public Object execute(final Object[] parameters) {
        return new CustomN1qlRepositoryQueryExecutor(couchbaseOperations, queryMethod, namedQueries).execute(parameters);
    }
}
