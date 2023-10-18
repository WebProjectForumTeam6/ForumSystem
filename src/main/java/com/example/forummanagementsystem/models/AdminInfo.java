package com.example.forummanagementsystem.models;

import jakarta.persistence.*;

import java.util.Objects;
@Entity
@Table(name = "admins")
public class AdminInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private int id;
    @OneToOne
    @JoinColumn(name= "user_id")
    private User user;
    @Column(name= "phone_number")
    private String phoneNumber;

    public AdminInfo() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminInfo admin = (AdminInfo) o;
        return Objects.equals(user, admin.user) && Objects.equals(phoneNumber, admin.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, phoneNumber);
    }
}
