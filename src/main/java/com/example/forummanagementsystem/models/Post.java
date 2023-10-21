package com.example.forummanagementsystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    @JsonIgnore
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User createdBy;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;


    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)

    private Set<Comment> comments;
//todo
//Set<Tag> tags

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "likes",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))

    private Set<User> likes = new HashSet<>();

    public Post() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public int getLikes() {
        return likes.size();
    }

    public void setLikes(User user) {
        likes.add(user);
    }
   public void removeLikes(User user){
        likes.remove(user);
    }
//    public int getLikesCount(){
//        return likes.size();
//    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

