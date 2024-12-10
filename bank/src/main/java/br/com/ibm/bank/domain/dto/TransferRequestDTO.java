package br.com.ibm.bank.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferRequestDTO {

    private Integer accountNumber;
    private Double amount;

    public TransferRequestDTO(Integer accountNumber, Double amount) {
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public TransferRequestDTO() {
    }
}
