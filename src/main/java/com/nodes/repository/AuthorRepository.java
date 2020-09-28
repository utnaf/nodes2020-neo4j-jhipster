package com.nodes.repository;

import com.nodes.domain.Author;

import org.neo4j.springframework.data.repository.Neo4jRepository;
import org.neo4j.springframework.data.repository.query.Query;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * Spring Data Neo4j repository for the Author entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuthorRepository extends Neo4jRepository<Author, String> {
}
