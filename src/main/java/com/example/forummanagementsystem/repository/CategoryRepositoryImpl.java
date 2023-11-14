package com.example.forummanagementsystem.repository;

import com.example.forummanagementsystem.exceptions.EntityNotFoundException;
import com.example.forummanagementsystem.models.Category;
import com.example.forummanagementsystem.repository.contracts.CategoryRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    private final SessionFactory sessionFactory;
    @Autowired
    public CategoryRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Category> getAll() {
        try (Session session=sessionFactory.openSession()){
            Query<Category> query = session.createQuery("from Category", Category.class);
            return query.list();
        }
    }

    @Override
    public Category getById(int id) {
        try(Session session=sessionFactory.openSession()){
            Category category=session.get(Category.class, id);
            if (category==null){
                throw new EntityNotFoundException("Category", "id", id);
            }
            return category;
        }
    }
}
