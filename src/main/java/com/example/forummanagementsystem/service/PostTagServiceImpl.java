package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.exceptions.EntityNotFoundException;
import com.example.forummanagementsystem.models.PostTag;
import com.example.forummanagementsystem.models.Tag;
import com.example.forummanagementsystem.repository.PostRepository;
import com.example.forummanagementsystem.repository.PostTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostTagServiceImpl implements PostTagService {

    private final PostTagRepository postTagRepository;
    private final PostRepository postRepository;

    public PostTagServiceImpl(PostTagRepository postTagRepository, PostRepository postRepository) {
        this.postTagRepository = postTagRepository;
        this.postRepository = postRepository;
    }

    @Override
    public List<Tag> getAllTags(){
        return postTagRepository.getAllTags();
    }

    @Override
    public Tag getTagById(int id){
        return postTagRepository.getTagById(id);
    }
    @Override
    public Tag getTagByName(String name){
        return postTagRepository.getTagByName(name);
    }

    public Tag create(Tag tag) {
        boolean duplicateExist = true;
        try {
            postTagRepository.getTagByName(tag.getContent());
        } catch (EntityNotFoundException e) {
            duplicateExist = false;
        }
            return postTagRepository.getTagByName(tag.getContent());
        }

    }





