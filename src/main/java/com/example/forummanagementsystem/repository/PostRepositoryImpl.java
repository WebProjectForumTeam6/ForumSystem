package com.example.forummanagementsystem.repository;

import com.example.forummanagementsystem.exceptions.EntityNotFoundException;
import com.example.forummanagementsystem.models.FilterOptions;
import com.example.forummanagementsystem.models.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepositoryImpl implements PostRepository {

    private final SessionFactory sessionFactory;

    @Autowired

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

    @Override
    public List<Post> getAll(FilterOptions FilterOptions) {
        try(Session session = sessionFactory.openSession()) {
            List<String> filters = new ArrayList<>();
            Map<String, Object> params = new HashMap<>();

            FilterOptions.getTitle().ifPresent(value -> {
                filters.add("title like :title");
                params.put("title", String.format("%%%s%%", value));
            });

           FilterOptions.getContent().ifPresent(value -> {
                filters.add("content like :content");
                params.put("content", String.format("%%%s%%", value));
            });


            StringBuilder queryString = new StringBuilder("FROM Post");

            if (!filters.isEmpty()) {
                queryString.append(" where ").append(String.join(" and ", filters));
            }


            queryString.append(generateOrderBy(FilterOptions));

            Query<Post> query = session.createQuery(queryString.toString(),Post.class);
            query.setProperties(params);
            return query.list();
        }
    }


    @Override
    public Post get(String title) {
        try (Session session = sessionFactory.openSession()) {
            Query<Post> query = session.createQuery(
                    "from Post where title= :title", Post.class);
            query.setParameter("title", title);

            List<Post> result = query.list();
            if (result.isEmpty()) {
                throw new EntityNotFoundException("Post", "title", title);
            }
            return result.get(0);
        }
    }

    @Override
    public void create(Post post) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(post);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(int id) {
        Post postToDelete = getById(id);
        try (Session session = sessionFactory.openSession()) {
            deleteTags(id);
            session.beginTransaction();
            session.remove(postToDelete);
            session.getTransaction().commit();
        }

    }
    private void deleteTags(int id){
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Query<?> query =session.createNativeQuery(
                    "delete from forum.posts_tags where post_id= :id",Post.class);
            query.setParameter("id",id);
            query.executeUpdate();
            session.getTransaction().commit();

        }

    }

    @Override
    public void update(Post post){
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.merge(post);
            session.getTransaction().commit();
        }
    }

    public String generateOrderBy(FilterOptions postFilterOptions) {
        if (postFilterOptions.getSortBy().isEmpty()) {
            return "";
        }

        String orderBy = "";
        switch (postFilterOptions.getSortBy().get()) {
            case "title":
                orderBy = "title";
                break;
            case "content":
                orderBy = "content";

        }

        orderBy = String.format(" order by %s", orderBy);

        if (postFilterOptions.getSortOrder().isPresent() && postFilterOptions.getSortOrder().get().equalsIgnoreCase("desc")) {
            orderBy = String.format("%s desc", orderBy);
        }

        return orderBy;
    }
}







