package com.example.forummanagementsystem.repository;

import com.example.forummanagementsystem.models.Comment;
import com.example.forummanagementsystem.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepositoryImpl implements CommentRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public CommentRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Comment> getAllComments() {
        try (Session session = sessionFactory.openSession()) {
            Query<Comment> query = session.createQuery("from Comment", Comment.class);
            return query.list();
        }
    }

    @Override
    public List<Comment> getUserComments(User user) {
        try (Session session= sessionFactory.openSession()){
            Query<Comment> query=session.createQuery("from Comment where user=:user", Comment.class);
            query.setParameter("user", user);
            return query.list();
        }
    }
}
