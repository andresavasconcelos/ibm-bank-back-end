package br.com.ibm.bank.service.transaction;

import br.com.ibm.bank.domain.entity.Account;
import br.com.ibm.bank.domain.entity.Transaction;
import jakarta.xml.bind.ValidationException;

public interface ITransactionService {

     void creditBalance(Integer id, Double amountToCredit) throws ValidationException;
    void debitBalance(Integer id, Double amountToDebit);
}
