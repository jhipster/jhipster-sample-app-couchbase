package io.github.jhipster.sample.repository;

import io.github.jhipster.sample.domain.Authority;
import org.springframework.stereotype.Repository;

/**
 * Spring Data Couchbase repository for the Authority entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuthorityRepository extends JHipsterCouchbaseRepository<Authority, String> {}
