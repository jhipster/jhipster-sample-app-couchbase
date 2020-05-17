package io.github.jhipster.sample.config;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import org.assertj.core.util.Lists;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.config.BeanNames;
import org.springframework.data.couchbase.config.CouchbaseConfigurer;
import org.testcontainers.couchbase.BucketDefinition;
import org.testcontainers.couchbase.CouchbaseContainer;

import java.util.List;

@Configuration
public class DatabaseConfigurationIT extends AbstractCouchbaseConfiguration {

    private CouchbaseProperties couchbaseProperties;

    private static CouchbaseContainer couchbaseContainer;

    private static CouchbaseEnvironment couchbaseEnvironment;

    private static CouchbaseCluster couchbaseCluster;

    public DatabaseConfigurationIT(CouchbaseProperties couchbaseProperties) {
        this.couchbaseProperties = couchbaseProperties;
    }

    @Override
    @Bean(destroyMethod = "", name = BeanNames.COUCHBASE_ENV)
    public CouchbaseEnvironment couchbaseEnvironment() {
        return getCouchbaseEnvironment();
    }

    @Override
    public Cluster couchbaseCluster() throws Exception {
        return getCouchbaseCluster();
    }

    @Override
    protected List<String> getBootstrapHosts() {
        return Lists.newArrayList(getCouchbaseContainer().getContainerIpAddress());
    }

    @Override
    protected String getBucketName() {
        return couchbaseProperties.getBucket().getName();
    }

    @Override
    protected String getBucketPassword() {
        return couchbaseProperties.getBucket().getPassword();
    }

    @Override
    protected CouchbaseConfigurer couchbaseConfigurer() {
        return this;
    }

    private CouchbaseContainer getCouchbaseContainer() {
        if (couchbaseContainer == null) {
            couchbaseContainer = new CouchbaseContainer("couchbase:6.5.1")
                .withBucket(new BucketDefinition(getBucketName()).withQuota(100))
                .withCredentials(getUsername(), getBucketPassword());
            couchbaseContainer.start();
        }
        return couchbaseContainer;
    }

    private CouchbaseEnvironment getCouchbaseEnvironment() {
         if (couchbaseEnvironment == null) {
             couchbaseEnvironment = DefaultCouchbaseEnvironment
                 .builder()
                 .bootstrapCarrierDirectPort(getCouchbaseContainer().getBootstrapCarrierDirectPort())
                 .bootstrapHttpDirectPort(getCouchbaseContainer().getBootstrapHttpDirectPort())
                 .build();
         }
         return couchbaseEnvironment;
    }

    private CouchbaseCluster getCouchbaseCluster() {
        if (couchbaseCluster == null) {
            couchbaseCluster = CouchbaseCluster.create(couchbaseEnvironment(),
                getCouchbaseContainer().getContainerIpAddress()
            );
        }
        return couchbaseCluster;
    }
}
