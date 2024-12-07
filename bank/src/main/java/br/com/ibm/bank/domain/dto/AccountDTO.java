package br.com.ibm.bank.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {

    private Integer numberAccount;
    private String branch;
    private String accountType;
    private Double balance;

    public AccountDTO(Integer numberAccount, String branch, String accountType, Double balance) {
        this.numberAccount = numberAccount;
        this.branch = branch;
        this.accountType = accountType;
        this.balance = balance;
    }
    public AccountDTO() {

    }

}
