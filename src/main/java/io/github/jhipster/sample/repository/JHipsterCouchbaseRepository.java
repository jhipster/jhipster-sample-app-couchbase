package io.github.jhipster.sample.repository;

import com.couchbase.client.java.query.QueryScanConsistency;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.couchbase.repository.*;
import org.springframework.data.domain.*;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Couchbase specific {@link org.springframework.data.repository.Repository} interface that use KV first approach to optimize requests.
 */
@NoRepositoryBean
@ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
public interface JHipsterCouchbaseRepository<T, ID> extends CouchbaseRepository<T, ID> {
    String FIND_IDS_QUERY = "SELECT meta().id as __id, 0 as __cas FROM #{#n1ql.bucket} WHERE #{#n1ql.filter}";

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
        return entities.stream().map(entity -> (ID) getEntityInformation().getId(entity)).collect(Collectors.toList());
    }
}
