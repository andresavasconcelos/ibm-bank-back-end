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
@Table(name="tbl_customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CUSTOMER")
    private Integer id;

    @Column(name = "DOCUMENT", nullable = false, length = 11, unique = true)
    private String document;

    @Column(name = "NAME", nullable = false, length = 255)
    private String name;

    @Column(name = "AGE", nullable = false)
    private Integer age;

    @Column(name = "EMAIL", nullable = false, length = 255)
    private String email;

    @Column(name = "CREATE_DATE")
    private LocalDate createDate;

    @Column(name = "UPDATE_DATE")
    private LocalDate updateDate;

    @ManyToMany
    @JoinTable(name = "tbl_customers_accounts",
                joinColumns = @JoinColumn(name="ID_CUSTOMER"),
                inverseJoinColumns = @JoinColumn(name="ID_ACCONT"))
    private List<Account> accounts;
}