package br.com.ibm.bank.repository;

import br.com.ibm.bank.domain.entity.Customer;
import br.com.ibm.bank.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByIdAccount(Integer idAccount);
}