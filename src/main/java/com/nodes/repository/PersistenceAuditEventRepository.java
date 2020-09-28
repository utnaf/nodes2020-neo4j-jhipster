package com.nodes.repository;

import com.nodes.domain.PersistentAuditEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.neo4j.springframework.data.repository.Neo4jRepository;

import java.time.Instant;
import java.util.List;

/**
 * Spring Data Neo4j repository for the {@link PersistentAuditEvent} entity.
 */
public interface PersistenceAuditEventRepository extends Neo4jRepository<PersistentAuditEvent, String> {

    List<PersistentAuditEvent> findByPrincipal(String principal);

    List<PersistentAuditEvent> findByPrincipalAndAuditEventDateAfterAndAuditEventType(String principal, Instant after, String type);

    Page<PersistentAuditEvent> findAllByAuditEventDateBetween(Instant fromDate, Instant toDate, Pageable pageable);

    List<PersistentAuditEvent> findByAuditEventDateBefore(Instant before);
}
