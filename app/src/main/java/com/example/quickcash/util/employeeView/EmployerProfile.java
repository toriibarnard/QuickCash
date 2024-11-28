package com.example.quickcash.util.employeeView;

public class EmployerProfile {
    private String name;
    private String email;
    private String phone;
    private String rating;

    public EmployerProfile(String name, String email, String phone, String rating) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.rating = rating != null ? rating : "No rating available";
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getRating() {
        return rating;
    }
}