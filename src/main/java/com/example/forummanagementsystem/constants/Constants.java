package com.example.forummanagementsystem.constants;

public class Constants {
    public static class AuthenticationHelper {
        public static final String AUTHORIZATION_HEADER_NAME = "Authorization";
        public static final String INVALID_AUTHENTICATION_ERROR = "Invalid authentication.";
    }
    public static class User {
        public static final String MODIFY_USER_ERROR_MESSAGE = "Only admin can block user!";
    }
    public static class CommentService{
        public static final String ADMIN_OR_CREATOR = "You are not and admin or creator.";
        public static final String NOT_AN_ADMIN = "You are not an admin.";
    }
}
