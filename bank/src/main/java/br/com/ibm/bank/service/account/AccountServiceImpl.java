package br.com.ibm.bank.service.account;

import br.com.ibm.bank.domain.dto.AccountDTO;
import br.com.ibm.bank.domain.entity.Account;
import br.com.ibm.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountServiceImpl implements IAccountService{

    @Autowired
    private AccountRepository repository;

    @Override
    public void create(Account newAccount) {
        repository.save(newAccount);
    }

    @Override
    public AccountDTO getInformationAccount(Integer id) {

        AccountDTO info = new AccountDTO();
        Account account = repository.findByNumberAccount(id);

        if(account != null){

            info.setNumberAccount(account.getNumberAccount());
            info.setBranch(account.getBranch());
            info.setAccountType(account.getAccountType());
            info.setBalance(account.getBalance());
        } else {
//            Exception
        }

        return info;
    }
}
