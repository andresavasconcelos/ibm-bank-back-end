package br.com.ibm.bank.service.transaction;

import br.com.ibm.bank.domain.entity.Account;
import br.com.ibm.bank.domain.entity.Transaction;
import jakarta.xml.bind.ValidationException;

import java.util.List;

public interface ITransactionService {

     void creditBalance(Integer id, Double amountToCredit);
    void debitBalance(Integer id, Double amountToDebit) throws ValidationException;
    List<Transaction> extract(Integer idAccount) throws ValidationException;
}
