package com.nodes.config;

import io.github.jhipster.config.JHipsterConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.neo4j.springframework.data.repository.config.EnableNeo4jRepositories;


@Configuration
@EnableNeo4jRepositories("com.nodes.repository")
public class DatabaseConfiguration {

    private final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);
}
