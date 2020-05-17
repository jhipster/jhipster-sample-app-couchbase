package io.github.jhipster.sample.repository;

import io.github.jhipster.sample.domain.Authority;


/**
 * Spring Data Couchbase repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends N1qlCouchbaseRepository<Authority, String> {
}
