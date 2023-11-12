package com.example.forummanagementsystem.repository;

import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.Tag;
import com.example.forummanagementsystem.models.User;

import java.util.List;

public interface PostTagRepository {
    List<Tag> getAllTags();

    Tag getTagById(int id);

    Tag getTagByContent(String content);

    Tag create(Tag tag);

    Tag update(Tag tag);

    void delete(Tag tag);

    Post modifyPostTags(Post post);

}
