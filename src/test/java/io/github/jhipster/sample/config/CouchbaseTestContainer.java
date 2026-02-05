package io.github.jhipster.sample.config;

import org.slf4j.LoggerFactory;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.couchbase.BucketDefinition;
import org.testcontainers.couchbase.CouchbaseContainer;
import org.testcontainers.junit.jupiter.Container;

public interface CouchbaseTestContainer {
    String BUCKET_NAME = "testBucket";

    @Container
    CouchbaseContainer couchbaseContainer = new CouchbaseContainer("couchbase/server:8.0.0")
        .withBucket(new BucketDefinition(BUCKET_NAME))
        .withLogConsumer(new Slf4jLogConsumer(LoggerFactory.getLogger(CouchbaseTestContainer.class)));

    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.couchbase.connection-string", couchbaseContainer::getConnectionString);
        registry.add("spring.couchbase.username", couchbaseContainer::getUsername);
        registry.add("spring.couchbase.password", couchbaseContainer::getPassword);
        registry.add("jhipster.database.couchbase.bucket-name", () -> BUCKET_NAME);
        registry.add("jhipster.database.couchbase.scope-name", () -> "testScope");
    }
}
