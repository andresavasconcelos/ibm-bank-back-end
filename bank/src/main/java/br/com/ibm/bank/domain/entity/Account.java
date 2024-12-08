package br.com.ibm.bank.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.text.DecimalFormat;
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

    @Column(name = "NUMBER_ACCOUNT", nullable = false)
    private Integer numberAccount;

    @Column(name = "BRANCH", nullable = false, length = 45)
    private String branch = "1234567";

    @Column(name = "ACCOUNT_TYPE", nullable = false)
    private String accountType;

    @Column(name = "BALANCE", nullable = false)
    private Double balance;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "CREATE_DATE", nullable = false)
    private LocalDate createDate;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions;


}
