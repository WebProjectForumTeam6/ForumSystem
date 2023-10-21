package com.example.forummanagementsystem.repository;

import com.example.forummanagementsystem.exceptions.EntityNotFoundException;
import com.example.forummanagementsystem.models.PostTag;
import com.example.forummanagementsystem.models.Tag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostTagRepositoryImpl implements PostTagRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public PostTagRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public PostTag get(int postId, int tagId) {
        try (Session session = sessionFactory.openSession()) {
            Query<PostTag> query = session.createQuery(
                    "from PostTag where postId= :postId and tagId= :tagId",
                    PostTag.class);
            query.setParameter("postId", postId);
            query.setParameter("tagId", tagId);

            return query.list().stream()
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException("PostTag", "post ID", String.valueOf(postId)));
        }
    }

    @Override
    public List<PostTag> getAllTagsForPost(int postId) {
        try (Session session = sessionFactory.openSession()) {
            Query<PostTag> query = session.createQuery(
                    "from PostTag where postId= :postId", PostTag.class);
            query.setParameter("postId", postId);
            return query.list();
        }
    }

    @Override
    public List<PostTag> getPostsByTagsId(int tagId) {
        try (Session session = sessionFactory.openSession()) {
            Query<PostTag> query = session.createQuery(
                    "from PostTag where tagId= :tagId", PostTag.class);
            query.setParameter("tagId", tagId);
            return query.list();
        }
    }

    @Override
    public void create(PostTag postTag) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(postTag);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(PostTag postTag) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(postTag);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(int postId, int tagId) {
        PostTag postTag = get(postId, tagId);
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.detach(postTag);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteAllTagsForPost(int postId) {
        try (Session session = sessionFactory.openSession()) {
            Query<PostTag> query = session.createQuery(
                    "from PostTag where postId= :postId", PostTag.class);
            query.setParameter("postId", postId);
            List<PostTag> list = query.list();
            for (PostTag postTag : list) {
                delete(postId, postTag.getTagId());
            }
        }
    }

    @Override
    public List<Tag> getAllTags() {
        try (Session session = sessionFactory.openSession()) {
            Query<Tag> query = session.createQuery(
                    "from Tag ", Tag.class);
            return query.list();
        }
    }

    @Override
    public Tag getTagById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Tag tag = session.get(Tag.class, id);
            if (tag == null) {
                throw new EntityNotFoundException("Tag", id);
            }
            return tag;
        }
    }

    @Override
    public Tag getTagByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<Tag> query = session.createQuery(
                    "from Tag where content= :name", Tag.class);
            query.setParameter("name", name);
            List<Tag> result = query.list();
            if (result.isEmpty()) {
                throw new EntityNotFoundException("Tag", "name", name);
            }
            return result.get(0);
        }
    }

    @Override
    public Tag create(Tag tag) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(tag);
            session.getTransaction().commit();
        }
        return tag;
    }
}

//todo

//    public String generateOrderBy(FilterOptions tagFilterOptions){
//        if(tagFilterOptions.getSortBy().isEmpty()) {
//            return "";
//        }
//        String orderBy ="";
//        if (tagFilterOptions.getSortBy().get().equals("content")) {
//            orderBy = "content";
//        }
//       return orderBy = String.format(" order by %s",orderBy);
//    }















