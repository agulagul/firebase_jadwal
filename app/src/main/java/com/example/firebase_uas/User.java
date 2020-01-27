package com.example.firebase_uas;

import com.google.firebase.database.PropertyName;

/**
 * Created by Belal on 4/15/2018.
 */

public class User {
    public String name, username, email, password, nim, role;

    public User(){

    }

    public User(String name, String username, String email, String password, String nim, String role) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.nim = nim;
        this.role = role;
    }

    @PropertyName("name")
    public String getName() {
        return name;
    }

    @PropertyName("name")
    public void setName(String name) {
        this.name = name;
    }

    @PropertyName("username")
    public String getUsername() {
        return username;
    }

    @PropertyName("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @PropertyName("email")
    public String getEmail() {
        return email;
    }

    @PropertyName("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @PropertyName("password")
    public String getPassword() {
        return password;
    }

    @PropertyName("password")
    public void setPassword(String password) {
        this.password = password;
    }

    @PropertyName("nim")
    public String getNim() {
        return nim;
    }

    @PropertyName("nim")
    public void setNim(String nim) {
        this.nim = nim;
    }

    @PropertyName("role")
    public String getRole() {
        return role;
    }

    @PropertyName("role")
    public void setRole(String role) {
        this.role = role;
    }
}
