package br.com.ibm.bank.service.account;

import br.com.ibm.bank.domain.dto.AccountDTO;
import br.com.ibm.bank.domain.entity.Account;

public interface IAccountService {

    AccountDTO findAccount(Integer id);

}
