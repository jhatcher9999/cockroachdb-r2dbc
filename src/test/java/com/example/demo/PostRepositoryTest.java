package com.example.demo;

import io.r2dbc.spi.ConnectionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import reactor.test.StepVerifier;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataR2dbcTest
public class PostRepositoryTest {

    @Autowired
    DatabaseClient client;

    @Autowired
    PostRepository posts;

    @Test
    public void testDatabaseClientExisted() {
        assertNotNull(client);
    }

    @Test
    public void testPostRepositoryExisted() {
        assertNotNull(posts);
    }

    @Test
    public void testInsertAndQuery() {
        this.client.sql("insert into posts ( title, content ) values ( :t, :c )")
                .bind("t", "testtitle")
                .bind("c", "testcontent")
                .then().block(Duration.ofSeconds(5));

        this.posts.findByTitleContains("testtitle")
                .take(1)
                .as(StepVerifier::create)
                .consumeNextWith(p -> assertEquals("testtitle", p.getTitle()))
                .verifyComplete();
    }
}