package br.com.ibm.bank.service.transaction;

import br.com.ibm.bank.domain.entity.Account;
import br.com.ibm.bank.domain.entity.Transaction;
import br.com.ibm.bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class TransactionServiceImpl implements ITransactionService{

    @Autowired
    private TransactionRepository repository;

    @Override
    public void creditBalance(Integer id, Double amountToCredit) {
        Account account = repository.findByIdAccount(id);
        Transaction transaction = new Transaction();
        //Colocar uma validação na controller se a conta existe
        if(!account.getBalance().equals(0.00) ){
          Double resultBalance = account.getBalance() - amountToCredit;
          //Todo: verificar se o valor deu negativo e se sim, resultar um erro.
          account.setBalance(resultBalance);

          transaction.setIdAccount(account.getId());
//          transaction.setTransactionType("creditar"); incluir o name
          transaction.setAmount(amountToCredit);
          transaction.setAccount(account);
          transaction.setUpdateDate(LocalDate.now());
//          transaction.setCreateDate(LocalDate.now()); ver se o default esta funcionando

          repository.save(transaction);
        } else {
            System.out.println("Não há saldo na conta");
        }

    }

    @Override
    public void debitBalance(Integer id, Double amountToDebit) {
        Account account = repository.findByIdAccount(id);
        //Colocar uma validação na controller se a conta existe

    }

    @Override
    public Transaction validBalance(Account account) {
        return null;
    }
}
