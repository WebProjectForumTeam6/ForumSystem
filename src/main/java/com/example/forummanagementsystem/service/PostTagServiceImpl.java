package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.exceptions.AuthorizationException;
import com.example.forummanagementsystem.models.*;
import com.example.forummanagementsystem.repository.PostRepository;
import com.example.forummanagementsystem.repository.PostTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostTagServiceImpl implements PostTagService {

    public static final String PERMISSION_ERROR = "You don't have permission.";
    public static final String ERROR_MESSAGE = "Only Admin or tag creator can modify the tag.";
    private final PostTagRepository postTagRepository;
    private final PostRepository postRepository;

    @Autowired
    public PostTagServiceImpl(PostTagRepository postTagRepository, PostRepository postRepository) {
        this.postTagRepository = postTagRepository;
        this.postRepository = postRepository;
    }

    @Override
    public List<Tag> getAllTags() {
        return postTagRepository.getAllTags();
    }

    @Override
    public Tag getTagById(int id) {
        return postTagRepository.getTagById(id);
    }

    @Override
    public void create(PostTag tag, User user) {
        if (user.isAdmin() || (!user.isBlocked())) {
            postTagRepository.create(tag);
        } else {
            throw new AuthorizationException(PERMISSION_ERROR);
        }
    }

    @Override
    public void deleteAllTagsForPost(int postId) {
        postTagRepository.deleteAllTagsForPost(postId);
    }

    //todo - add tag to DtoPost and Mapper
    @Override
    public void addTagToPost(int postId, int tagId) {
        Post post = postRepository.getById(postId);
        if (post.getTags().stream()
                .anyMatch(p -> p.getId() == postId)) {
            return;
        }
        PostTag postTag = new PostTag(postId, tagId);
        postTagRepository.create(postTag);
    }

    @Override
    public void removeTagFromPost(int postId, int tagId) {
        Post post = postRepository.getById(postId);
        if (post.getTags().stream()
                .noneMatch(p -> p.getId() == postId)) {
            return;
        }
        postTagRepository.delete(postId, tagId);
    }

    @Override
    public Tag updateTag(int tagId, String content, User user) {
        Tag tag = getTagById(tagId);
        tag.setContent(content);
        if ((!user.isAdmin() || (user.isBlocked()))) {
            throw new AuthorizationException(ERROR_MESSAGE);
        } else {
            return postTagRepository.update(tag);
        }
    }

}




