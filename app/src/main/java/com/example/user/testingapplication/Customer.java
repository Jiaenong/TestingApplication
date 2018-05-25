package com.example.user.testingapplication;

public class Customer {
    private String name;
    private String password;
    private String email;
    private String phone;

    public Customer()
    {

    }

    public Customer(String name, String password, String email, String phone)
    {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
