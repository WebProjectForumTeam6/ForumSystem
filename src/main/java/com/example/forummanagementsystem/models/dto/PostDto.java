package com.example.forummanagementsystem.models.dto;

import com.example.forummanagementsystem.models.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PostDto {

    @NotNull(message = "Title can't be empty.")
    @Size(min = 16, max = 64, message = "Title should be between 16 and 64 symbols.")
    private String title;

    @NotNull(message = "Content can't be empty.")
    @Size(min = 32, max = 8192, message = "Content should be between 32 and 8193 symbols.")
    private String content;

    @NotNull(message = "You should choose category.")
    private int categoryId;
    private String tags;

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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}