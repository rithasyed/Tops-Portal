package com.example.register.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reg")
public class Register {

    @Id
    private String _id;
    private String username;
    private String email;
    private String password;

    public Register(String _id, String username, String email, String password) {
        this._id = _id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public Register(){

    }

    public String get_id() {
        return _id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

