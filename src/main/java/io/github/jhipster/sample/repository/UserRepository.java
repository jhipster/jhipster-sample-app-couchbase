package io.github.jhipster.sample.repository;

import static io.github.jhipster.sample.config.Constants.ID_DELIMITER;

import com.couchbase.client.java.query.QueryScanConsistency;
import io.github.jhipster.sample.domain.User;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.couchbase.repository.ScanConsistency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * Spring Data Couchbase repository for the {@link User} entity.
 */
@Repository
public interface UserRepository extends JHipsterCouchbaseRepository<User, String> {
    // @ScanConsistency is to fix index issues with Spring Data Couchbase
    // https://github.com/spring-projects/spring-data-couchbase/issues/897

    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    Optional<User> findOneByActivationKey(String activationKey);

    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    List<User> findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant dateTime);

    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    Optional<User> findOneByResetKey(String resetKey);

    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    @Query("#{#n1ql.selectEntity} WHERE LOWER(email) = LOWER($1) AND #{#n1ql.filter}")
    Optional<User> findOneByEmailIgnoreCase(String email);

    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    default Optional<User> findOneByLogin(String login) {
        return findById(User.PREFIX + ID_DELIMITER + login);
    }

    default Page<User> findAllByActivatedIsTrue(Pageable pageable) {
        return new PageImpl<>(
            findAllByActivatedIsTrue(JHipsterCouchbaseRepository.pageableStatement(pageable)),
            pageable,
            countAllByActivatedIsTrue()
        );
    }

    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND activated = true #{[0]}")
    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    List<User> findAllByActivatedIsTrue(String pageableStatement);

    @Query("SELECT COUNT(*) as count FROM #{#n1ql.bucket} WHERE #{#n1ql.filter} AND activated = true")
    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    Long countAllByActivatedIsTrue();
}
