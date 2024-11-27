package org.usvm.spring.benchmarks.storage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.usvm.spring.benchmarks.model.Wallet;


public interface WalletRepository extends Repository<Wallet, Integer> {
	/**
	 * Retrieve an {@link Wallet} from the data store by id.
	 * @param id the id to search for
	 * @return the {@link Wallet} if found
	 */
	@Query("SELECT wallet FROM Wallet wallet left join fetch wallet.cards WHERE wallet.id =:id")
	@Transactional(readOnly = true)
	Wallet findById(@Param("id") Integer id);

	/**
	 * Save an {@link Wallet} to the data store, either inserting or updating it.
	 * @param wallet the {@link Wallet} to save
	 */
	void save(Wallet wallet);

	/**
	 * Returns all the wallets from data store
	 **/
	@Query("SELECT wallet FROM Wallet wallet")
	@Transactional(readOnly = true)
	Page<Wallet> findAll(Pageable pageable);

}
