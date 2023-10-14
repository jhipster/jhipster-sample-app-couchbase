package io.github.jhipster.sample.config;

import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.couchbase.*;
import org.testcontainers.utility.DockerImageName;

public class CouchbaseTestContainer implements InitializingBean, DisposableBean {

    private CouchbaseContainer couchbaseContainer;
    private static final Logger log = LoggerFactory.getLogger(CouchbaseTestContainer.class);

    @Override
    public void destroy() {
        //        if (null != couchbaseContainer && couchbaseContainer.isRunning()) {
        //            couchbaseContainer.stop();
        //        }
    }

    public String getBucketName() {
        return "testBucket";
    }

    @Override
    public void afterPropertiesSet() {
        if (null == couchbaseContainer) {
            DockerImageName dockerImage = DockerImageName.parse("couchbase/server:7.2.2").asCompatibleSubstituteFor("couchbase/server");
            couchbaseContainer =
                new CouchbaseContainer(dockerImage)
                    .withBucket(new BucketDefinition(getBucketName()))
                    .withCredentials("user", "password")
                    .withServiceQuota(CouchbaseService.SEARCH, 1024)
                    .withLogConsumer(new Slf4jLogConsumer(log))
                    .withStartupTimeout(Duration.ofMinutes(15))
                    .withReuse(true);
        }
        if (!couchbaseContainer.isRunning()) {
            couchbaseContainer.start();
        }
    }

    public CouchbaseContainer getCouchbaseContainer() {
        return couchbaseContainer;
    }
}
