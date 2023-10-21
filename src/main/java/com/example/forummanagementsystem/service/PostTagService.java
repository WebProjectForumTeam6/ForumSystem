package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.models.Tag;

import java.util.List;

public interface PostTagService {
    List<Tag> getAllTags();

    Tag getTagById(int id);

    Tag getTagByName(String name);
}
