package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.exceptions.AuthorizationException;
import com.example.forummanagementsystem.models.PostTag;
import com.example.forummanagementsystem.models.Tag;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.repository.PostRepository;
import com.example.forummanagementsystem.repository.PostTagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.forummanagementsystem.constants.Constants.CommentService.ADMIN_OR_CREATOR;

@Service
public class PostTagServiceImpl implements PostTagService {

    public static final String PERMISSION_ERROR = "You don't have permission.";
    private final PostTagRepository postTagRepository;
    private final PostRepository postRepository;

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
    public Tag getTagByName(String name) {
        return postTagRepository.getTagByName(name);
    }

    @Override
    public void create(PostTag tag, User user) {
        if (user.isAdmin() || user.isBlocked()) {
            postTagRepository.create(tag);
        } else {
            throw new AuthorizationException(PERMISSION_ERROR);
        }
    }

    @Override
    public void deleteAllTagsForPost(int postId) {
        postTagRepository.deleteAllTagsForPost(postId);
    }

}





