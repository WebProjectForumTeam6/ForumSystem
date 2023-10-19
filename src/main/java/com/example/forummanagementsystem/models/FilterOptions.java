package com.example.forummanagementsystem.models;

import java.util.Optional;
public class FilterOptions {

//    private Optional<User> createdBy;
    private Optional<String> title;
    private Optional<String> content;
//    private Optional<String> sortBy;
//    private Optional<String> sortOrder;

    public FilterOptions(
//            User createdBy,
            String title,
            String content
//            String sortBy,
//            String sortOrder
            ) {
//        this. createdBy= Optional.ofNullable(createdBy);
        this.title = Optional.ofNullable(title);
        this.content = Optional.ofNullable(content);
//        this.sortBy = Optional.ofNullable(sortBy);
//        this.sortOrder = Optional.ofNullable(sortOrder);

    }

//    public Optional<User> getCreatedBy() {
//        return createdBy;
//    }
    public Optional<String> getTitle() {
        return title;
    }

    public Optional<String> getContent(){
        return content;
    }

//    public Optional<String> getSortBy() {
//        return sortBy;
//    }
//
//    public Optional<String> getSortOrder() {
//        return sortOrder;
//    }
}
