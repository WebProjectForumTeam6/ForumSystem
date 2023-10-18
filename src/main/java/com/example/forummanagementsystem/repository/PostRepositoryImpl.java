package com.example.forummanagementsystem.repository;

import com.example.forummanagementsystem.exceptions.EntityNotFoundException;
import com.example.forummanagementsystem.models.Post;
import com.example.forummanagementsystem.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepositoryImpl implements PostRepository{
    private final SessionFactory sessionFactory;

    public PostRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Post getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Post post = session.get(Post.class, id);
            if (post == null) {
                throw new EntityNotFoundException("Post", id);
            }
            return post;
        }
    }
}
