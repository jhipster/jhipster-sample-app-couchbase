package io.github.jhipster.sample.config.couchbase;

import org.springframework.data.couchbase.repository.config.RepositoryOperationsMapping;
import org.springframework.data.couchbase.repository.support.CouchbaseRepositoryFactory;
import org.springframework.data.couchbase.repository.support.CouchbaseRepositoryFactoryBean;

public class CustomCouchbaseRepositoryFactoryBean extends CouchbaseRepositoryFactoryBean {

    /**
     * Creates a new {@link CouchbaseRepositoryFactoryBean} for the given repository interface.
     *
     * @param repositoryInterface must not be {@literal null}.
     */
    public CustomCouchbaseRepositoryFactoryBean(Class repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected CouchbaseRepositoryFactory getFactoryInstance(RepositoryOperationsMapping operationsMapping) {
        return new CustomCouchbaseRepositoryFactory(operationsMapping);
    }
}
