package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.exceptions.AuthorizationException;
import com.example.forummanagementsystem.exceptions.EntityDuplicateException;
import com.example.forummanagementsystem.exceptions.EntityNotFoundException;
import com.example.forummanagementsystem.models.FilterOptions;
import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {


    public static final String MODIFY_THE_POST = "Only Admin or post creator can modify the post.";
    public static final String PERMISSION_ERROR = "You don't have permission.";
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
        Post post = repository.getById(id);
        if (!(user.isAdmin() || post.getCreatedBy().equals(user))) {
            throw new AuthorizationException(MODIFY_THE_POST);
        }

    }


    @Override
    public Post getById(int id) {
        return repository.getById(id);
    }

    @Override
    public List<Post> get() {
        return null;
    }

    @Override
    public void create(Post post, User creator) {
        isUserBlocked(creator);
        repository.create(post);
    }
    private void isUserBlocked(User user){
        if (user.isBlocked()){
            throw new AuthorizationException(PERMISSION_ERROR);
        }
    }

    @Override
    public List<Post> getAll(FilterOptions filterOptions) {
        return repository.getAll(filterOptions);
    }

    public void createLike( int postID, User user){
        Post post = repository.getById(postID);
        post.getLikes().add(user);
        this.update(post,user);
    }
}
