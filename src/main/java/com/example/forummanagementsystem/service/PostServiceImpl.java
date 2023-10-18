package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.exceptions.AuthorizationException;
import com.example.forummanagementsystem.exceptions.EntityDuplicateException;
import com.example.forummanagementsystem.exceptions.EntityNotFoundException;
import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {


    public static final String MODIFY_THE_POST = "Only Admin or post creator can modify the post.";
    private final PostRepository repository;

    @Autowired
    public PostServiceImpl(PostRepository repository) {
        this.repository = repository;
    }

    public void delete(int id, User user) {
        checkModifyPermissions(id, user);
        repository.delete(id);
    }

    @Override
    public void update(Post post, User user) {
        checkModifyPermissions(post.getId(), user);

        boolean duplicateExists = true;

        try {
            Post excitingPost = repository.get(post.getTitle());
            if (excitingPost.getId() == post.getId()) {
                duplicateExists = false;
            }
        }catch (EntityNotFoundException e){
            duplicateExists =false;
        }
        if (duplicateExists){
            throw new EntityDuplicateException("Post","title",post.getTitle());
        }
        repository.update(post);
    }

    private void checkModifyPermissions(int id, User user) {
        Post post = repository.get(id);
        if (!(user.isAdmin() || post.getUser().equals(user))) {
            throw new AuthorizationException(MODIFY_THE_POST);
        }

    }


    @Override
    public Post get(int id) {
        return null;
    }

    @Override
    public List<Post> get(String username, String title, Integer like, String tags) {
        return null;
    }

    @Override
    public void create(Post post) {

    }
}
