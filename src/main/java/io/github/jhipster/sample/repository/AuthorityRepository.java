package io.github.jhipster.sample.repository;

import io.github.jhipster.sample.domain.Authority;
import org.springframework.stereotype.Repository;

/**
 * Spring Data Couchbase repository for the {@link Authority} entity.
 */
@Repository
public interface AuthorityRepository extends JHipsterCouchbaseRepository<Authority, String> {}
