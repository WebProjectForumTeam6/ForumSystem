INSERT INTO tags (content)
VALUES ('Tag 1'),
       ('Tag 2'),
       ('Tag 3');

INSERT INTO users (first_name, last_name, email, username, password, is_blocked, is_admin, profile_picture_url)
VALUES ('John', 'Doe', 'johndoe@example.com', 'johndoe', 'password123', 0, 1, 'profile_picture1.jpg'),
       ('Alice', 'Smith', 'alicesmith@example.com', 'alicesmith', 'securepass', 1, 0, NULL);

INSERT INTO admins_info (user_id, phone_number)
VALUES (1, '1234567890'),
       (2, '9876543210');

INSERT INTO posts (title, content, user_id)
VALUES ('Post 1', 'Content of Post 1', 1),
       ('Post 2', 'Content of Post 2', 2);

INSERT INTO comments (user_id, post_id, content)
VALUES (1, 1, 'Comment on Post 1'),
       (2, 1, 'Another comment on Post 1');

INSERT INTO downvote (post_id, user_id)
VALUES (1, 2),
       (2, 1);

INSERT INTO posts_tags (post_id, tag_id)
VALUES (1, 1),
       (1, 2),
       (2, 3);

INSERT INTO upvote (post_id, user_id)
VALUES (1, 2),
       (2, 1);
