package com.example.forummanagementsystem.service;

import com.example.forummanagementsystem.exceptions.AuthorizationException;
import com.example.forummanagementsystem.models.FilterOptions;
import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.dto.PostDto;
import com.example.forummanagementsystem.repository.contracts.PostRepository;
import com.example.forummanagementsystem.service.contracts.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {


    public static final String MODIFY_THE_POST = "Only Admin or post creator can modify the post.";
    public static final String PERMISSION_ERROR = "You don't have permission.";
    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Override
    public List<Post> getAll(FilterOptions filterOptions) {
        return postRepository.getAll(filterOptions);
    }

    @Override
    public Post getById(int id) {
        return postRepository.getById(id);
    }

    @Override
    public void create(Post post, User creator) {
        isUserBlocked(creator);
        postRepository.create(post);
    }

    @Override
    public Post update(PostDto postDto, User user, int postId) {
        checkModifyPermissions(postId, user);

        Post post = getById(postId);
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        return postRepository.update(post);
    }

    public void delete(int id, User user) {
        checkModifyPermissions(id, user);
        postRepository.delete(id);
    }

    @Override
    public void modifyLike(int id, User user) {
        isUserBlocked(user);
        Post postToModify = postRepository.getById(id);
        if (postToModify.getLikes().contains(user)) {
            postToModify.removeLikes(user);
        } else {
            postToModify.addLikes(user);
        }
        postRepository.modifyLike(postToModify);
    }


    @Override
    public List<Post> getTop10MostCommentedPosts() {
        return postRepository.getTop10MostCommentedPosts();
    }

    @Override
    public List<Post> get10MostRecentlyCreatedPosts() {
        return postRepository.get10MostRecentlyCreatedPosts();
    }

    private void checkModifyPermissions(int id, User user) {
        Post post = getById(id);
        if (!user.isAdmin() && !post.getCreatedBy().equals(user)) {
            throw new AuthorizationException(MODIFY_THE_POST);
        }
    }

    private void isUserBlocked(User user) {
        if (user.isBlocked()) {
            throw new AuthorizationException(PERMISSION_ERROR);
        }
    }
}