package io.github.jhipster.sample.repository;

import io.github.jhipster.sample.domain.BankAccount;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.stereotype.Repository;


/**
 * Spring Data Couchbase repository for the BankAccount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BankAccountRepository extends N1qlCouchbaseRepository<BankAccount, String> {

}
