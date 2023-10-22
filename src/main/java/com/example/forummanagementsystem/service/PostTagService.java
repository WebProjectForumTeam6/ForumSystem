package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.models.PostTag;
import com.example.forummanagementsystem.models.Tag;
import com.example.forummanagementsystem.models.User;

import java.util.List;

public interface PostTagService {
    List<Tag> getAllTags();

    Tag getTagById(int id);

    Tag getTagByName(String name);


    void create(PostTag tag, User user);

    void deleteAllTagsForPost(int postId);
}
