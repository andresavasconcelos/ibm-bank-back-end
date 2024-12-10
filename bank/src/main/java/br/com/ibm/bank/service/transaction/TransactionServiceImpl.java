package br.com.ibm.bank.service.transaction;

import br.com.ibm.bank.domain.entity.Account;
import br.com.ibm.bank.domain.entity.Transaction;
import br.com.ibm.bank.domain.enums.AccountStatus;
import br.com.ibm.bank.domain.enums.TransactionType;
import br.com.ibm.bank.repository.TransactionRepository;
import br.com.ibm.bank.service.account.IAccountService;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionServiceImpl implements ITransactionService{

    @Autowired
    private final TransactionRepository repository;

    @Autowired
    private final IAccountService accountService;

    @Autowired

    public TransactionServiceImpl(TransactionRepository repository, IAccountService accountService) {
        this.repository = repository;
        this.accountService = accountService;
    }

    @Override
    public void creditBalance(Integer accountNumber, Double amountToCredit) {

        Account account = accountService.findByNumberAccount(accountNumber);
        if(account != null && account.getStatus() == AccountStatus.ACTIVE.getValue()) {
            Double resultBalanceDebit = account.getBalance() + amountToCredit;
            account.setBalance(resultBalanceDebit);

            Transaction transaction = new Transaction();
            transaction.setIdAccount(account.getId());
            transaction.setTransactionType(TransactionType.DEPOSIT.getValue());
            transaction.setAmount(amountToCredit);
            transaction.setAccount(account);
            transaction.setUpdateDate(LocalDate.now());

            repository.save(transaction);
        }
    }

    @Override
    public void debitBalance(Integer accountNumber, Double amountToDebit) throws ValidationException {
        Account account = accountService.findByNumberAccount(accountNumber);

        if(account != null && account.getStatus() == AccountStatus.ACTIVE.getValue()){
            if(!account.getBalance().equals(0.00) ){
                Transaction transaction = new Transaction();
                transaction.setIdAccount(account.getId());
                transaction.setTransactionType(TransactionType.TRANSFER.getValue());
                transaction.setAmount(amountToDebit);
                transaction.setAccount(balanceCalculation(account, amountToDebit));
                transaction.setUpdateDate(LocalDate.now());

                repository.save(transaction);
            } else {
                throw new ValidationException("Não há saldo na conta");
            }
        } else {
            throw new ValidationException("Conta nao existe ou não está ativa");
        }
    }

    @Override
    public List<Transaction> extract(Integer idAccount) {

        List<Transaction> extract = repository.findByIdAccount(idAccount);


        return extract;
    }

    private Account balanceCalculation(Account account, Double amountToCredit) throws ValidationException {
        Double resultBalance = account.getBalance() - amountToCredit;

        if(resultBalance < 0){
            throw new ValidationException("Não há salvo para efetuar a transação");
        }
        account.setBalance(resultBalance);
        return account;
    }
}
