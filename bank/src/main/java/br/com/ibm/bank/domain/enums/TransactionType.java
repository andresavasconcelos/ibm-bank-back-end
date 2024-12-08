package br.com.ibm.bank.domain.enums;

public enum TransactionType {

    DEPOSIT(1, "DEPOSIT"),
    WITHDRAWAL(2, "WITHDRAWAL"),
    TRANSFER(3, "TRANSFER"),
    PAYMENT(4, "PAYMENT");

    private final int valor;
    private final String descricao;

    TransactionType(int valor, String descricao) {
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
