package io.github.jhipster.sample;

import io.github.jhipster.sample.config.AsyncSyncConfiguration;
import io.github.jhipster.sample.config.EmbeddedCouchbase;
import io.github.jhipster.sample.config.JacksonConfiguration;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tech.jhipster.config.JHipsterConstants;

/**
 * Base composite annotation for integration tests.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = { JhipsterCouchbaseSampleApplicationApp.class, JacksonConfiguration.class, AsyncSyncConfiguration.class })
@EmbeddedCouchbase
@ActiveProfiles(JHipsterConstants.SPRING_PROFILE_TEST)
public @interface IntegrationTest {
}
