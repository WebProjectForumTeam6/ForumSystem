package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.exceptions.EntityDuplicateException;
import com.example.forummanagementsystem.exceptions.EntityNotFoundException;
import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.Tag;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.TagDto;
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
    public Tag create(TagDto tagDto) {
        boolean duplicateExists = true;
        try {
            postTagRepository.getTagByContent(tagDto.getContent());
        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }
        if (duplicateExists) {
            throw new EntityDuplicateException("Tag", "content", tagDto.getContent());
        }
        Tag tag = new Tag();
        tag.setContent(tagDto.getContent());
        return postTagRepository.create(tag);
    }

    public Tag updateTag(int tagId, TagDto tagDto) {
        Tag tag = postTagRepository.getTagById(tagId);
        tag.setContent(tagDto.getContent());
        return postTagRepository.update(tag);
    }


    @Override
    public void delete(int tagId) {
        Tag tag = getTagById(tagId);
        postTagRepository.delete(tag);
    }


    @Override
    public Post addTagToPost(String tags, User user, Post post) {
        String[] array = tags.split(",");
        for (String tag : array) {
            try {
                Tag tagToAdd = postTagRepository.getTagByContent(tag);
                post.addTag(tagToAdd);
            } catch (EntityNotFoundException e) {
                if (!post.getTags().stream().map(Tag::getContent).toList().contains(tag)) {
                    Tag tagToAdd = new Tag();
                    tagToAdd.setContent(tag);
                    postTagRepository.create(tagToAdd);
                    post.addTag(tagToAdd);
                }
            }
        }
        return postTagRepository.modifyPostTags(post);
    }


    @Override
    public Post deleteTagFromPost(String tags, User user, Post post) {
        String[] array = tags.split(", ");
        for (String tag : array) {
            try {
                Tag tagToRemove = postTagRepository.getTagByContent(tag);
                if (post.getTags().contains(tagToRemove)) {
                    post.removeTag(tagToRemove);
                }

                if (tagToRemove.getPosts().contains(post)){
                    tagToRemove.removePost(post);
                }
            } catch (EntityNotFoundException ignored) {
            }
        }
        return postTagRepository.modifyPostTags(post);
    }

    @Override
    public Post deleteAllTagsFromPost(User user, Post post) {
        post.getTags().clear();
        return postRepository.update(post);
    }
}




