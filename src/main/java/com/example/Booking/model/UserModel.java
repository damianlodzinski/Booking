package com.example.Booking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private int id;
    private String login;
    private String password;
    private String role;

    public UserModel(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }
}
