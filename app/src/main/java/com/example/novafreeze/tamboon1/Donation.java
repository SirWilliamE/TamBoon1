package com.example.novafreeze.tamboon1;

public class Donation {

    private String name;

    private String token;

    private Integer amount;

    private boolean success;

    private String error_code;

    private String error_message;

    public boolean isSuccess() {
        return success;
    }

    public String getError_code() {
        return error_code;
    }

    public String getError_message() {
        return error_message;
    }

    public Donation(String name, String token, Integer amount) {
        this.name = name;
        this.token = token;
        this.amount = amount;
    }
}
