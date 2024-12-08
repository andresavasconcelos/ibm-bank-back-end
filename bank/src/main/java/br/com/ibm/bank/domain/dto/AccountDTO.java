package br.com.ibm.bank.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {

    private Integer numberAccount;
    private Integer branch;
    private Integer accountType;
    private Double balance;

    public AccountDTO(Integer numberAccount, Integer branch, Integer accountType, Double balance) {
        this.numberAccount = numberAccount;
        this.branch = branch;
        this.accountType = accountType;
        this.balance = balance;
    }
    public AccountDTO() {

    }

}
