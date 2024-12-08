package br.com.ibm.bank.domain.enums;

public enum AccountStatus {
    ACTIVE(1, "ACTIVE"),
    BLOCKED(2, "BLOCKED"),
    CLOSED(3, "CLOSED");

    private final int valor;
    private final String descricao;

    AccountStatus(int valor, String descricao) {
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
