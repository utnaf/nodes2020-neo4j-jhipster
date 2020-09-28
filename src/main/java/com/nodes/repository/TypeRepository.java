package com.nodes.repository;

import com.nodes.domain.Type;

import org.neo4j.springframework.data.repository.Neo4jRepository;
import org.neo4j.springframework.data.repository.query.Query;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * Spring Data Neo4j repository for the Type entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeRepository extends Neo4jRepository<Type, String> {
}
