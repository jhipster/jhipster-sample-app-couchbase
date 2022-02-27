package io.github.jhipster.sample;

import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.couchbase.*;
import org.testcontainers.utility.DockerImageName;

public class CouchbaseTestContainerExtension implements BeforeAllCallback {

    private static AtomicBoolean started = new AtomicBoolean(false);

    private static CouchbaseContainer couchbase;

    private String getBucketName() {
        return "testBucket";
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        if (!started.get()) {
            DockerImageName dockerImage = DockerImageName.parse("couchbase/server:7.0.3").asCompatibleSubstituteFor("couchbase/server");
            couchbase =
                new CouchbaseContainer(dockerImage)
                    .withBucket(new BucketDefinition(getBucketName()).withQuota(100))
                    .withServiceQuota(CouchbaseService.SEARCH, 1024)
                    .withCredentials("user", "secret");
            couchbase.start();
            System.setProperty("spring.couchbase.connection-string", couchbase.getConnectionString());
            System.setProperty("spring.couchbase.username", couchbase.getUsername());
            System.setProperty("spring.couchbase.password", couchbase.getPassword());
            System.setProperty("jhipster.database.couchbase.bucket-name", getBucketName());
            System.setProperty("jhipster.database.couchbase.scope-name", "testScope");
            started.set(true);
        }
    }
}
