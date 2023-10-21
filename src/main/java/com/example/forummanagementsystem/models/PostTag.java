package com.example.forummanagementsystem.models;


import jakarta.persistence.*;

import java.util.Objects;
@Entity
@Table(name = "posts_tags")
public class PostTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")

    private int id;

    @JoinColumn (name = "post_id")

    private int postId;


    @JoinColumn(name = "tag_id")

    private int tagId;

    public PostTag() {
    }

    public PostTag (int postId, int tagId) {
        this.postId = postId;
        this.tagId = tagId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostTag postTag = (PostTag) o;
        return id == postTag.id && Objects.equals(tagId, postTag.tagId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
