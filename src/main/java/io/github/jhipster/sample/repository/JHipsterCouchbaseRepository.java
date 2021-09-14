package io.github.jhipster.sample.repository;

import static java.lang.String.format;

import com.couchbase.client.java.query.QueryScanConsistency;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.ScanConsistency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Couchbase specific {@link org.springframework.data.repository.Repository} interface uses N1QL for all requests.
 */
@NoRepositoryBean
public interface JHipsterCouchbaseRepository<T, ID> extends CouchbaseRepository<T, ID> {
    static String pageableStatement(Pageable pageable) {
        return pageableStatement(pageable, "");
    }

    static String pageableStatement(Pageable pageable, String prefix) {
        Sort sort = Sort.by(
            pageable
                .getSort()
                .stream()
                .map(order -> {
                    String property = order.getProperty();
                    if ("id".equals(property)) {
                        return order.withProperty(format("meta(%s).id", prefix));
                    }
                    if (prefix.isEmpty()) {
                        return order;
                    }
                    return order.withProperty(format("%s.%s", prefix, property));
                })
                .collect(Collectors.toList())
        );
        return new Query().limit(pageable.getPageSize()).skip(pageable.getOffset()).with(sort).export();
    }

    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    List<T> findAll();

    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    List<T> findAll(Sort sort);

    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    Page<T> findAll(Pageable pageable);

    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    void deleteAll();
}
