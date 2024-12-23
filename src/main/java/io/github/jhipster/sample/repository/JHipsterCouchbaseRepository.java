package io.github.jhipster.sample.repository;

import static java.lang.String.format;

import com.couchbase.client.java.query.QueryScanConsistency;
import java.util.List;
import org.springframework.data.couchbase.repository.*;
import org.springframework.data.domain.*;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Couchbase specific {@link org.springframework.data.repository.Repository} interface that use KV first approach to optimize requests.
 */
@NoRepositoryBean
@ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
public interface JHipsterCouchbaseRepository<T, ID> extends CouchbaseRepository<T, ID> {
    String FIND_IDS_QUERY =
        "SELECT meta().id as __id, 0 as __cas FROM #{#n1ql.bucket}.#{#n1ql.scope}.#{#n1ql.collection} WHERE #{#n1ql.filter}";

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
                .toList()
        );
        return new org.springframework.data.couchbase.core.query.Query()
            .limit(pageable.getPageSize())
            .skip(pageable.getOffset())
            .with(sort)
            .export();
    }

    default List<T> findAll() {
        return findAllById(toIds(findAllIds()));
    }

    default Page<T> findAll(Pageable pageable) {
        Page<T> page = findAllIds(pageable);
        return new PageImpl<>(findAllById(toIds(page.getContent())), pageable, page.getTotalElements());
    }

    default List<T> findAll(Sort sort) {
        return findAllById(toIds(findAllIds(sort)));
    }

    default void deleteAll() {
        deleteAllById(toIds(findAllIds()));
    }

    @Query(FIND_IDS_QUERY)
    List<T> findAllIds();

    @Query(FIND_IDS_QUERY)
    Page<T> findAllIds(Pageable pageable);

    @Query(FIND_IDS_QUERY)
    List<T> findAllIds(Sort sort);

    @SuppressWarnings("unchecked")
    default List<ID> toIds(List<T> entities) {
        return entities.stream().map(entity -> (ID) getEntityInformation().getId(entity)).toList();
    }
}
