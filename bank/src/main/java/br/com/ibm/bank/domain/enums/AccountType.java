package br.com.ibm.bank.domain.enums;

public enum AccountType {

    CHECKIG(1, "CHECKING"),
    SAVINGS(2, "SAVINGS"),
    INVESTMENT(3, "INVETMENT");

    private final int valor;
    private final String descricao;

    AccountType(int valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public int getValor() {
        return this.valor;
    }
    public String getDescricao() {
        return this.descricao;
    }

}
