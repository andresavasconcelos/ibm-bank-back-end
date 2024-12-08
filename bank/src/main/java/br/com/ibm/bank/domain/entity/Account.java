package br.com.ibm.bank.domain.entity;

import br.com.ibm.bank.domain.enums.AccountStatus;
import br.com.ibm.bank.domain.enums.AccountType;
import br.com.ibm.bank.domain.enums.Branch;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name="tbl_accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ACCOUNT")
    private Integer id;

    @Column(name = "NUMBER_ACCOUNT", nullable = false, unique = true)
    private Integer numberAccount;

    @Column(name = "BRANCH", nullable = false, length = 45)
    private Integer branch;

    @Column(name = "ACCOUNT_TYPE", nullable = false)
    private Integer accountType;

    @Column(name = "BALANCE", nullable = false)
    private Double balance;

    @Column(name = "STATUS", nullable = false)
    private Integer status;

    @Column(name = "CREATE_DATE", nullable = false)
    private LocalDate createDate;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("account")
    private List<Transaction> transactions;

    public void setStatus(AccountStatus accountStatusEnum) {
        this.status = accountStatusEnum.getValue();
        this.status = (status != null) ? accountStatusEnum.getValue() : null;
    }

    public void setAccountType(AccountType accountTypeEnum) {
        this.accountType = accountTypeEnum.getValue();
        this.accountType = (accountType != null) ? accountTypeEnum.getValue() : null;
    }

    public void setBranchType(Branch branchEnum) {
        this.branch = branchEnum.getValue();
        this.branch = (branch != null) ? branchEnum.getValue() : null;
    }

}
