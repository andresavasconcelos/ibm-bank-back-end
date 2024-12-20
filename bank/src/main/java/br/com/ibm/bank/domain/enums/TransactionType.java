package br.com.ibm.bank.domain.enums;

public enum TransactionType {

    DEPOSIT(1, "DEPOSIT"),
    WITHDRAWAL(2, "WITHDRAWAL"),
    TRANSFER(3, "TRANSFER"),
    PAYMENT(4, "PAYMENT");

    private final int value;
    private final String description;

    TransactionType(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return this.value;
    }
    public String getDescription() {
        return this.description;
    }

}
