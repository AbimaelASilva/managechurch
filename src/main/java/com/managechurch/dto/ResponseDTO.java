package com.managechurch.dto;

public class ResponseDTO {
    private String status;
    private Object body;

    // Construtor padrão
    public ResponseDTO() {
    }

    // Construtor com parâmetros
    public ResponseDTO(String status, Object body) {
        this.status = status;
        this.body = body;
    }

    // Getters e Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}