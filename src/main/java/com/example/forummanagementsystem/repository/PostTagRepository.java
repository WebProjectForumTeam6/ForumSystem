package com.example.forummanagementsystem.repository;

import com.example.forummanagementsystem.models.PostTag;
import com.example.forummanagementsystem.models.Tag;

import java.util.List;

public interface PostTagRepository {
    PostTag get(int postId, int tagId);

    List<PostTag> getAllTagsForPost(int postId);

    List<PostTag> getPostsByTagsId(int tagId);

    void create(PostTag postTag);

    void update(PostTag postTag);

    void delete(int postId, int tagId);

    void deleteAllTagsForPost(int postId);

    List<Tag> getAllTags();

    Tag getTagById(int id);


    Tag getTagByName(String name);

    Tag create(Tag tag);
}
