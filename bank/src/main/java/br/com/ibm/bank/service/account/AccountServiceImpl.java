package br.com.ibm.bank.service.account;

import br.com.ibm.bank.domain.dto.AccountDTO;
import br.com.ibm.bank.domain.entity.Account;
import br.com.ibm.bank.domain.enums.AccountStatus;
import br.com.ibm.bank.domain.enums.AccountType;
import br.com.ibm.bank.domain.enums.Branch;
import br.com.ibm.bank.repository.AccountRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class AccountServiceImpl implements IAccountService{

    protected  static final Logger log = LogManager.getLogger();
    @Autowired
    private final AccountRepository repository;
    public final Double noBalance = 0.00;
    private static Integer currentNumber = 1000;

    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Account create(String endDocument) {
        Account account = new Account();

        Integer numberAccount = generateAccountNumber(endDocument);

        log.info("numberAccount " + numberAccount);

        account.setNumberAccount(numberAccount);
        account.setBranch(Branch.BRANCH_VILMARIANA_SAOPAULO.getValue());
        account.setAccountType(AccountType.SAVINGS);
        account.setBalance(noBalance);
        account.setStatus(AccountStatus.ACTIVE);
        account.setCreateDate(LocalDate.now());
        account = repository.save(account);

        return account;
    }

    @Override
    public AccountDTO findAccount(Integer id) {

        AccountDTO info = new AccountDTO();
        Account account = findByNumberAccount(id);

        if(account != null){
            info.setNumberAccount(account.getNumberAccount());
            info.setBranch(account.getBranch());
            info.setAccountType(account.getAccountType());
            info.setBalance(account.getBalance());
        } else {
            log.info("Account not found, {}" + id);
            throw  new ValidationException("Account not found");
        }

        return info;
    }

    @Override
    public Account findByNumberAccount(Integer id){
        return repository.findByNumberAccount(id);
    }

    public static synchronized Integer generateAccountNumber(String endDocument) {
        String currentNumberResult = currentNumber + endDocument;
        return Integer.valueOf(currentNumberResult);
    }
}
