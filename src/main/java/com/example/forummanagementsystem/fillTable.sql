use forum;
INSERT INTO tags (content)
VALUES ('Tag 1'),
       ('Tag 2'),
       ('Tag 3');

INSERT INTO users (first_name, last_name, email, username, password, is_blocked, is_admin, profile_picture_url)
VALUES ('John', 'Doe', 'johndoe@example.com', 'johndoe', 'password123', 0, 1, 'profile_picture1.jpg'),
       ('Alice', 'Smith', 'alicesmith@example.com', 'alicesmith', 'securepass', 1, 0, NULL),
       ('Maria','Sanchez','mariasanchez@example.com','mariasanchez','maria123',0,0,null),
       ('Georgi', 'Stoyanov','georgistoyanov@example.com','georgist','georgi1234',0,0,'profile_picture2.jpg');


INSERT INTO admins_info (user_id, phone_number)
VALUES (1, '1234567890'),
       (2, '9876543210'),
       (3, '1245789552'),
       (4, '9897885698');

INSERT INTO posts (title, content,post_timestamp, user_id)
VALUES ('Post 1', 'Content of Post 1','2023-05-01 19:09:24', 1),
       ('Post 2', 'Content of Post 2','2023-07-24 19:09:14', 2),
       ('Post 3', 'Content of Post 2','2023-10-21 19:09:10', 3),
       ('Post 4', 'Content of Post 2', 4),
       ('Post 5', 'Content of Post 2', 2);
INSERT INTO posts (title, content,post_timestamp, user_id)
VALUES ('Post 1', 'Content of Post 1','2023-05-01 19:09:24', 1);

INSERT INTO comments (user_id, post_id, content)
VALUES (1, 1, 'Comment on Post 1'),
       (2, 1, 'Another comment on Post 1'),
       (4, 2, 'Comment on Post 2'),
       (3, 1, 'Comment on Post 1'),
       (4, 1, 'Comment on Post 1');


INSERT INTO posts_tags (post_id, tag_id)
VALUES (1, 1),
       (1, 2),
       (3, 3),
       (4, 3),
       (5, 3);

INSERT INTO likes (post_id, user_id)
VALUES (1, 2),
       (3, 1),
       (4, 3),
       (5, 3);
