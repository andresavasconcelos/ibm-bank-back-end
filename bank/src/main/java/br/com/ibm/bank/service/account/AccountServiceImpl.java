package br.com.ibm.bank.service.account;

import br.com.ibm.bank.domain.dto.AccountDTO;
import br.com.ibm.bank.domain.entity.Account;
import br.com.ibm.bank.repository.AccountRepository;
import jakarta.validation.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class AccountServiceImpl implements IAccountService{

    protected  static final Logger log = LogManager.getLogger();

    private AccountRepository repository;
    public final Double noBalance = 0.00;
    public final String branch = "12345678";
    private static Integer currentNumber = 1000;

    public Account create() {
        Account account = new Account();
        account.setNumberAccount(generateAccountNumber());
        account.setBranch(branch);
        account.setAccountType("2");
//      TODO: Incluir variaveis locais e também ajustar o Enum
        account.setBalance(noBalance);
        account.setStatus("1");
        account.setCreateDate(LocalDate.now());
        account = repository.save(account);

        return account;
    }

    @Override
    public AccountDTO findAccount(Integer id) {

        AccountDTO info = new AccountDTO();
        Account account = repository.findByNumberAccount(id);

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

    public static synchronized Integer generateAccountNumber() {
        currentNumber++;
        return currentNumber;
    }
}
