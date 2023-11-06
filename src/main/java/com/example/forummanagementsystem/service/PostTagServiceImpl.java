package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.exceptions.EntityDuplicateException;
import com.example.forummanagementsystem.exceptions.EntityNotFoundException;
import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.Tag;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.TagDto;
import com.example.forummanagementsystem.repository.PostTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PostTagServiceImpl implements PostTagService {

    public static final String PERMISSION_ERROR = "You don't have permission.";
    public static final String ERROR_MESSAGE = "Only Admin or tag creator can modify the tag.";
    private final PostTagRepository postTagRepository;


    @Autowired
    public PostTagServiceImpl(PostTagRepository postTagRepository) {
        this.postTagRepository = postTagRepository;

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
        Tag tag= new Tag();
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
                Tag tagToAdd = new Tag();
                tagToAdd.setContent(tag);
                postTagRepository.create(tagToAdd);
                post.addTag(tagToAdd);
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
                if (post.getTags().contains(tagToRemove)){
                    post.removeTag(tagToRemove);
                }
            } catch (EntityNotFoundException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        }
        return postTagRepository.modifyPostTags(post);
    }
}




