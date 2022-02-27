package io.github.jhipster.sample.repository;

import io.github.jhipster.sample.domain.User;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data Couchbase repository for the {@link User} entity.
 */
@Repository
public interface UserRepository extends JHipsterCouchbaseRepository<User, String> {
    default Optional<User> findOneByActivationKey(String activationKey) {
        return findIdByActivationKey(activationKey).map(User::getId).flatMap(this::findById);
    }

    @Query(FIND_IDS_QUERY + " AND activationKey = $1")
    Optional<User> findIdByActivationKey(String activationKey);

    default List<User> findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant dateTime) {
        return findAllById(toIds(findAllIdsByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(dateTime)));
    }

    @Query(FIND_IDS_QUERY + " AND activated = false AND activationKey IS NOT NULL AND createdDate < $1")
    List<User> findAllIdsByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant dateTime);

    default Optional<User> findOneByResetKey(String resetKey) {
        return findIdByResetKey(resetKey).map(User::getId).flatMap(this::findById);
    }

    @Query(FIND_IDS_QUERY + " AND resetKey = $1")
    Optional<User> findIdByResetKey(String resetKey);

    default Optional<User> findOneByEmailIgnoreCase(String email) {
        return findIdByEmailIgnoreCase(email).map(User::getId).flatMap(this::findById);
    }

    @Query(FIND_IDS_QUERY + " AND LOWER(email) = LOWER($1)")
    Optional<User> findIdByEmailIgnoreCase(String email);

    default Optional<User> findOneByLogin(String login) {
        return findById(login);
    }

    default Page<User> findAllByActivatedIsTrue(Pageable pageable) {
        Page<User> page = findAllIdsByActivatedIsTrue(pageable);
        return new PageImpl<>(findAllById(toIds(page.getContent())), pageable, page.getTotalElements());
    }

    @Query(FIND_IDS_QUERY + " AND activated = true")
    Page<User> findAllIdsByActivatedIsTrue(Pageable pageable);
}
