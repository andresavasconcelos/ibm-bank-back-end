package br.com.ibm.bank.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

    @ManyToOne
    @JoinColumn(name = "TBL_ACCOUNTS_ID_ACCONT")
    @JsonIgnoreProperties("transactions")
    private Account account;

}
