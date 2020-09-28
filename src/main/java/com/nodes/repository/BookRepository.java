package com.nodes.repository;

import com.nodes.domain.Book;

import org.neo4j.springframework.data.repository.Neo4jRepository;
import org.neo4j.springframework.data.repository.query.Query;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * Spring Data Neo4j repository for the Book entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookRepository extends Neo4jRepository<Book, String> {
}
