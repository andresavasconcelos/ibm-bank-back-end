package br.com.ibm.bank.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.text.DecimalFormat;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "tbl_transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TRANSACTION")
    private Integer id;

    @Column(name = "ID_ACCOUNT")
    private Integer idAccount;

    @Column(name = "TRANSACTION_TYPE")
    private Integer transactionType;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "UPDATE_DATE")
    private LocalDate updateDate;

    @Column(name = "CREATE_DATE")
    private LocalDate createDate;

    @ManyToOne
    @JoinColumn(name = "tbl_accounts")
    private Account account;

}
