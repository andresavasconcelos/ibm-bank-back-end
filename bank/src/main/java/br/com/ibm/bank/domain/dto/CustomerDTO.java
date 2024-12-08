package br.com.ibm.bank.domain.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CustomerDTO {

    private String name;
    private Integer age;
    private String email;

    public CustomerDTO(String name, Integer age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
    public CustomerDTO() {

    }


}
