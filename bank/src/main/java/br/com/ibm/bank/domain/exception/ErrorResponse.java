package br.com.ibm.bank.domain.exception;

public class ErrorResponse {

    private String error;
    private String message;

    public ErrorResponse(String error, String message) {
        this.error = error;
        this.message = message;
    }

}
