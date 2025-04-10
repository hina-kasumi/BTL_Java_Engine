package com.hina.dto.request;

public class RegisterRequest {
    private String username;
    private String password;
    private int coin;

    public RegisterRequest(String username, String password, int coin) {
        this.username = username;
        this.password = password;
        this.coin = coin;
    }

    public RegisterRequest() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }
}
