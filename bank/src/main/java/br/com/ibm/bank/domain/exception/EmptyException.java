package br.com.ibm.bank.domain.exception;

public class EmptyException extends Exception{
    private Integer code;
    private String description;

    public EmptyException(String message, Integer code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
