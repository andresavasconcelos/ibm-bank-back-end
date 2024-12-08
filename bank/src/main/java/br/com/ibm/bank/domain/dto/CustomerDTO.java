package br.com.ibm.bank.domain.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CustomerDTO {

    private String document;
    private String name;
    private Integer age;
    private String email;

    public CustomerDTO(String document,String name, Integer age, String email) {
        this.document = document;
        this.name = name;
        this.age = age;
        this.email = email;
    }
    public CustomerDTO() {

    }


}
