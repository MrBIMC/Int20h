package com.knightsofnull.int20h.authentication;

public class User {
    private String name;
    private String surname;
    private String email;
    private String password;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }


    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return this.surname;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }
}
