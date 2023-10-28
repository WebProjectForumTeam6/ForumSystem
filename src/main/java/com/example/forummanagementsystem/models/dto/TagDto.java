package com.example.forummanagementsystem.models.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.PostMapping;

public class TagDto {
    @NotNull
    private String content;

    public TagDto() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
