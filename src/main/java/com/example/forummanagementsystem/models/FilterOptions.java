package com.example.forummanagementsystem.models;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public class FilterOptions {
    private Optional<String> createdBy;
    private Optional<String> title;
    private Optional<String> content;
    private Optional<String> category;
    private Optional<String> minDate;
    private Optional<String> maxDate;
    private Optional<String> sortBy;
    private Optional<String> sortOrder;

    public FilterOptions(
            String createdBy,
            String title,
            String content,
            String category,
            String minDate,
            String maxDate,
            String sortBy,
            String sortOrder
    ) {
        this.createdBy = Optional.ofNullable(createdBy);
        this.title = Optional.ofNullable(title);
        this.content = Optional.ofNullable(content);
        this.category=Optional.ofNullable(category);
        this.minDate=Optional.ofNullable(minDate);
        this.maxDate=Optional.ofNullable(maxDate);
        this.sortBy = Optional.ofNullable(sortBy);
        this.sortOrder = Optional.ofNullable(sortOrder);
    }

    public Optional<String> getCreatedBy() {
        return createdBy;
    }

    public Optional<String> getTitle() {
        return title;
    }

    public Optional<String> getContent() {
        return content;
    }

    public Optional<String> getCategory() {
        return category;
    }

    public Optional<String> getMinDate() {
        return minDate;
    }

    public Optional<String> getMaxDate() {
        return maxDate;
    }

    public Optional<String> getSortBy() {
        return sortBy;
    }

    public Optional<String> getSortOrder() {
        return sortOrder;
    }
}
