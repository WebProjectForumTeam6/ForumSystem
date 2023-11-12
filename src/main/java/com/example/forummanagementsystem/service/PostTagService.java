package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.Tag;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.TagDto;

import java.util.List;

public interface PostTagService {
    List<Tag> getAllTags();

    Tag getTagById(int id);

    Tag create(TagDto tagDto);

    Tag updateTag(int tagId, TagDto tagDto);

    void delete(int tagId);

    Post addTagToPost(String tags, User user, Post post);
    Post deleteTagFromPost(String tags, User user, Post post);
    Post deleteAllTagsFromPost(User user, Post post);

}
