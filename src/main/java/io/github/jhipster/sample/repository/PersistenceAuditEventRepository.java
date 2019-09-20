package io.github.jhipster.sample.repository;

import io.github.jhipster.sample.domain.PersistentAuditEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;

/**
 * Spring Data Couchbase repository for the {@link PersistentAuditEvent} entity.
 */
public interface PersistenceAuditEventRepository extends N1qlCouchbaseRepository<PersistentAuditEvent, String> {

    List<PersistentAuditEvent> findByPrincipal(String principal);

    List<PersistentAuditEvent> findByPrincipalAndAuditEventDateAfterAndAuditEventType(String principal, Instant after, String type);

    Page<PersistentAuditEvent> findAllByAuditEventDateBetween(Instant fromDate, Instant toDate, Pageable pageable);

    List<PersistentAuditEvent> findByAuditEventDateBefore(Instant before);
}
