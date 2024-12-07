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

    @Column(name = "NUMBER_ACCOUNT")
    private Integer numberAccount;

    @Column(name = "BRANCH")
    private String branch;

    @Column(name = "ACCOUNT_TYPE")
    private String accountType;

    @Column(name = "BALANCE")
    private Double balance;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CREATE_DATE")
    private LocalDate createDate;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Transaction> transactions;


}
