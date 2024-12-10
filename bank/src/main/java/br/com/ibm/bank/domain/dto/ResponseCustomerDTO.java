package br.com.ibm.bank.domain.dto;

import br.com.ibm.bank.domain.entity.Account;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseCustomerDTO {

    private Integer id;
    private String document;
    private String name;
    private Integer age;
    private String email;
    private List<Account> accounts;

    public ResponseCustomerDTO(Integer id, String document, String name, Integer age, String email, List<Account> accounts) {
        this.id = id;
        this.document = document;
        this.name = name;
        this.age = age;
        this.email = email;
        this.accounts = accounts;
    }

    public ResponseCustomerDTO() {

    }
}
