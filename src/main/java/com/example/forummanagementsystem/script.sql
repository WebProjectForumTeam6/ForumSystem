drop database if exists forum;

create database forum;

use forum;

create table categories
(
    category_id   int auto_increment
        primary key,
    category_name varchar(255) null
);

create table tags
(
    tag_id  int auto_increment
        primary key,
    content varchar(30) not null
);

create table users
(
    user_id             int auto_increment
        primary key,
    first_name          varchar(32)          not null,
    last_name           varchar(32)          not null,
    email               varchar(64)          not null,
    username            varchar(32)          not null,
    password            varchar(32)          not null,
    is_blocked          tinyint(1) default 0 not null,
    is_admin            tinyint(1) default 0 not null,
    profile_picture_url varchar(10000)       null
);

create table admins_info
(
    admin_id     int auto_increment
        primary key,
    user_id      int         null,
    phone_number varchar(10) not null,
    constraint admins_users_fk
        foreign key (user_id) references users (user_id)
);

create table posts
(
    post_id        int auto_increment
        primary key,
    title          varchar(64)   not null,
    content        varchar(8192) not null,
    post_timestamp datetime      not null,
    user_id        int           not null,
    category_id    int           not null,
    constraint posts_categories_category_id_fk
        foreign key (category_id) references categories (category_id),
    constraint posts_users_user_id_fk
        foreign key (user_id) references users (user_id)
);

create table comments
(
    comment_id        int auto_increment
        primary key,
    user_id           int           not null,
    post_id           int           not null,
    content           varchar(8192) not null,
    comment_timestamp datetime      not null,
    constraint comments_posts_fk
        foreign key (post_id) references posts (post_id),
    constraint comments_users_fk
        foreign key (user_id) references users (user_id)
);

create table likes
(
    id      int auto_increment
        primary key,
    post_id int not null,
    user_id int not null,
    constraint likes_posts_post_id_fk
        foreign key (post_id) references posts (post_id),
    constraint likes_users_user_id_fk
        foreign key (user_id) references users (user_id)
);

create table posts_tags
(
    id      int auto_increment
        primary key,
    post_id int null,
    tag_id  int not null,
    constraint posts_tags_posts_post_id_fk
        foreign key (post_id) references posts (post_id),
    constraint posts_tags_tags_tag_id_fk
        foreign key (tag_id) references tags (tag_id)
);

