package com.nodes.repository;

import com.nodes.domain.Authority;

import org.neo4j.springframework.data.repository.Neo4jRepository;
import java.util.List;

/**
 * Spring Data Neo4j RX repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends Neo4jRepository<Authority, String> {
    // See https://github.com/neo4j/sdn-rx/issues/51    List<Authority> findAll();
}
