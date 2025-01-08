package com.example.valerastores.Model;

public class  My_Models {

    String email,password,name;

    public My_Models(String name, String email, String password) {
        this.password = password;
        this.email = email;
        this.name = name;
    }

    public My_Models() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
