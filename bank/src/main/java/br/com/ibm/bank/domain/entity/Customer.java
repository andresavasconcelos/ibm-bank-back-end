package br.com.ibm.bank.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name="tbl_accounts")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_CUSTOMER")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AGE")
    private Integer age;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "CREATE_DATE")
    private LocalDate createDate;

    @Column(name = "UPDATE_DATE")
    private LocalDate updateDate;

    @ManyToMany
    @JoinTable(name = "tbl_customers_accounts",
                joinColumns = @JoinColumn(name="id_customer"),
                inverseJoinColumns = @JoinColumn(name="id_account"))
    private List<Account> accounts;
}