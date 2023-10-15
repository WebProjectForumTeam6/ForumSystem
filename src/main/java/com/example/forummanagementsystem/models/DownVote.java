package com.example.forummanagementsystem.models;

import jakarta.persistence.*;

import java.util.Objects;
@Entity
@Table(name = "downvotes")
public class DownVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "downvote_id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public DownVote() {
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DownVote downVote = (DownVote) o;
        return id == downVote.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
