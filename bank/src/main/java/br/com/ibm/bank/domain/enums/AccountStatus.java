package br.com.ibm.bank.domain.enums;

public enum AccountStatus {
    ACTIVE(1, "ACTIVE"),
    BLOCKED(2, "BLOCKED"),
    CLOSED(3, "CLOSED");

    private final int value;
    private final String description;

    AccountStatus(int value, String description) {
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
