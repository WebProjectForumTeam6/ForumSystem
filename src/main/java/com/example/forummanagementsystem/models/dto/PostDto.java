package com.example.forummanagementsystem.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PostDto {

    @NotNull(message = "Title can't be empty.")
    @Size(min = 16, max = 64, message = "Title should be between 16 and 64 symbols.")
    private String title;

    @NotNull(message = "Content can't be empty.")
    @Size(min = 32, max = 8192, message = "Content should be between 32 and 8193 symbols.")
    private String content;

    private String comments;

    private int likes;

    public PostDto() {
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}