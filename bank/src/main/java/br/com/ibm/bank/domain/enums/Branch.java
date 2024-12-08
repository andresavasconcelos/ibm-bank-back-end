package br.com.ibm.bank.domain.enums;

public enum Branch {
    BRANCH_VILMARIANA_SAOPAULO(1, "VILMARIANA"),
    BRANCH_TAMANDUATEI_SAOPAULO(1, "TAMANDUATEI_SAOPAULO");

    private final int value;
    private final String description;

    Branch(int value, String description){
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return this.value;
    }
    public String getDescriptiono() {
        return this.description;
    }

}
