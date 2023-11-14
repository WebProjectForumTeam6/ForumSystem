package com.example.forummanagementsystem.repository;

import com.example.forummanagementsystem.exceptions.EntityNotFoundException;
import com.example.forummanagementsystem.models.AdminInfo;
import com.example.forummanagementsystem.models.User;
import com.example.forummanagementsystem.models.UserFilterOptions;
import com.example.forummanagementsystem.repository.contracts.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User", User.class);
            return query.list();
        }
    }
    @Override
    public List<User> get(UserFilterOptions userFilterOptions) {
        try (
                Session session = sessionFactory.openSession()) {
            List<String> filters = new ArrayList<>();
            Map<String, Object> params = new HashMap<>();

            userFilterOptions.getFirstName().ifPresent(value -> {
                filters.add("firstName like :firstName ");
                params.put("firstName", String.format("%%%s%%", value));
            });
            userFilterOptions.getEmail().ifPresent(value -> {
                filters.add("email like :email ");
                params.put("email", String.format("%%%s%%", value));
            });
            userFilterOptions.getUsername().ifPresent(value -> {
                filters.add("username like :username ");
                params.put("username", String.format("%%%s%%", value));
            });
            StringBuilder queryString = new StringBuilder("from User ");
            if (!filters.isEmpty()) {
                queryString.append(" where ")
                        .append(String.join(" and ", filters));
            }

            Query<User> query = session.createQuery(queryString.toString(), User.class);
            query.setProperties(params);
            List <User> users=query.list();
            return query.list();
        }
    }
    @Override
    public User getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);
            if (user == null) {
                throw new EntityNotFoundException("User", id);
            }
            return user;
        }
    }

    @Override
    public User getByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User where username= :username", User.class);
            query.setParameter("username", username);

            List<User> result = query.list();
            if (result.isEmpty()) {
                throw new EntityNotFoundException("User", "username", username);
            }
            return result.get(0);
        }
    }

    public AdminInfo getAdminInfo(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<AdminInfo> query = session.createQuery("from AdminInfo where user= :user", AdminInfo.class);
            query.setParameter("user", user);
            List<AdminInfo> result = query.list();
            if (result.isEmpty()) {
                return null;
            } else {
                return result.get(0);
            }
        }
    }


    @Override
    public User create(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        }
        return user;
    }

    @Override
    public User block(User userToBlock) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(userToBlock);
            session.getTransaction().commit();
        }
        return userToBlock;
    }

    @Override
    public User makeAdmin(User userToMakeAdmin) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(userToMakeAdmin);
            session.getTransaction().commit();
        }
        return userToMakeAdmin;
    }

    @Override
    public User unblock(User userToUnblock) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(userToUnblock);
            session.getTransaction().commit();
        }
        return userToUnblock;
    }

    @Override
    public User getByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User where email = :email", User.class);
            query.setParameter("email", email);

            List<User> result = query.list();
            if (result.isEmpty()) {
                throw new EntityNotFoundException("User", "email", email);
            }
            return result.get(0);
        }
    }

    @Override
    public User getByFirstName(String firstName) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User where firstName = :firstName", User.class);
            query.setParameter("firstName", firstName);

            List<User> result = query.list();
            if (result.isEmpty()) {
                throw new EntityNotFoundException("User", "firstName", firstName);
            }
            return result.get(0);
        }
    }

    @Override
    public void updateUser(User updatedUser) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(updatedUser);
            session.getTransaction().commit();
        }
    }

    @Override
    public void addPhoneNumber(AdminInfo adminInfo) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(adminInfo);
            session.getTransaction().commit();
        }
    }

    @Override
    public void updatePhoneNumber(AdminInfo adminInfo) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(adminInfo);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public User addProfilePhoto(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(user);
            session.getTransaction().commit();
        }
        return user;
    }
}

