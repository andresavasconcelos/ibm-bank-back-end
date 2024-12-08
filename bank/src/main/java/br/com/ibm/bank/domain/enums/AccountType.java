package br.com.ibm.bank.domain.enums;

public enum AccountType {

    CHECKIG(1, "CHECKING"),
    SAVINGS(2, "SAVINGS"),
    INVESTMENT(3, "INVETMENT");

    private final int value;
    private final String description;

    AccountType(int valor, String description) {
        this.value = valor;
        this.description = description;
    }

    public int getValue() {
        return this.value;
    }
    public String getDescription() {
        return this.description;
    }

}
