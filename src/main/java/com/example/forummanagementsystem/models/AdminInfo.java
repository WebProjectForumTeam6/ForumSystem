package com.example.forummanagementsystem.models;

import jakarta.persistence.*;

import java.util.Objects;
@Entity
@Table(name = "admins_info")
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        AdminInfo adminInfo = (AdminInfo) o;
        return Objects.equals(user, adminInfo.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, phoneNumber);
    }
}
