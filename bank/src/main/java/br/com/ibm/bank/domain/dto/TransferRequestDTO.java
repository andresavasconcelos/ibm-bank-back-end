package br.com.ibm.bank.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferRequestDTO {

    private Integer id;
    private Double amount;

    public TransferRequestDTO(Integer id, Double amount) {
        this.id = id;
        this.amount = amount;
    }

    public TransferRequestDTO() {
    }
}
