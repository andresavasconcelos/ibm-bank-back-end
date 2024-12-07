package br.com.ibm.bank.service.transaction;

import br.com.ibm.bank.domain.entity.Account;
import br.com.ibm.bank.domain.entity.Transaction;

public interface ITransactionService {

     void creditBalance(Integer id, Double amountToCredit);
    void debitBalance(Integer id, Double amountToDebit);
    Transaction validBalance(Account account);
}
