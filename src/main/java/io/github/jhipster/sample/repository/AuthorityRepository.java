package io.github.jhipster.sample.repository;

import com.couchbase.client.java.query.QueryScanConsistency;
import io.github.jhipster.sample.domain.Authority;
import java.util.List;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.couchbase.repository.ScanConsistency;
import org.springframework.stereotype.Repository;

/**
 * Spring Data Couchbase repository for the {@link Authority} entity.
 */
@Repository
public interface AuthorityRepository extends JHipsterCouchbaseRepository<Authority, String> {
    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter}")
    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    List<Authority> findAll();
}
