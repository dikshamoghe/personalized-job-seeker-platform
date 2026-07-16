package com.careersync.dto;

public class LoginResponse {
    private String message;
    private String token;

    public LoginResponse(){}
    public LoginResponse(String message,String token){
        this.message=message;
        this.token=token;
    }

    public String getMessage(){
        return message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
