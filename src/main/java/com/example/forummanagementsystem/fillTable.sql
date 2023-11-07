use forum;
INSERT INTO tags (content)
VALUES ('bitcoin'),
       ('trade'),
       ('app'),
       ('crypto'),
       ('market');

INSERT INTO users (first_name, last_name, email, username, password, is_blocked, is_admin, profile_picture_url)
VALUES ('John', 'Doe', 'johndoe@example.com', 'johndoe', 'password123', 0, 1, 'profile_picture1.jpg'),
       ('Alice', 'Smith', 'alicesmith@example.com', 'alicesmith', 'securepass', 1, 0, NULL),
       ('Maria','Sanchez','mariasanchez@example.com','mariasanchez','maria123',0,0,null),
       ('Georgi', 'Stoyanov','georgistoyanov@example.com','georgist','georgi1234',0,0,'profile_picture2.jpg'),
       ('Berta','Morar','berta.morar@example.com','bertamorar','berta123456',0,1,NULL),
       ('Serena','Hayes','serena.hayes@example.com','serenaHayes1','serena09876',1,0,'profile_picture3.jpg'),
       ('Zella','Kertzman','zella.kertzman@example.com','zella.kertzman','1234zella',0,1,NULL);


INSERT INTO admins_info (user_id, phone_number)
VALUES (1, '1234567890'),
       (5, '9876543210'),
       (7, '1245789552');

INSERT INTO forum.posts (post_id, title, content, post_timestamp, user_id, category_id) VALUES (1, 'Trade only Bitcoin', 'Trading is a lifetime skill that is always beneficial if you know what you are doing, the biggest mistake of traders is trading alt that most times doesn\'t obey market structure,
                                                                                                   when it comes to cryptocurrency, trade only Bitcoin.', '2023-05-01 19:09:24', 1, 1);
INSERT INTO forum.posts (post_id, title, content, post_timestamp, user_id, category_id) VALUES (2, 'How long to learn trading?', 'Do you think that trading can be learnt well in a short time like three weeks for a quick trader?', '2023-07-24 19:09:14', 2, 1);
INSERT INTO forum.posts (post_id, title, content, post_timestamp, user_id, category_id) VALUES (3, 'Binance is Rolling Out Copy Trading?', 'did Binance just announce they\'re finally jumping on the copy trading feature?', '2023-10-21 19:09:10', 3, 1);
INSERT INTO forum.posts (post_id, title, content, post_timestamp, user_id, category_id) VALUES (4, 'App for crypto indicators', 'I try to find some apps for indicators. ', '2023-07-24 19:09:14', 4, 1);
INSERT INTO forum.posts (post_id, title, content, post_timestamp, user_id, category_id) VALUES (5, 'For funded traders / my top 3 prop firm', '1-Crypto fund trader 2-Funded next 3-The Funded Trader', '2023-10-21 19:09:10', 2, 1);
INSERT INTO forum.posts (post_id, title, content, post_timestamp, user_id, category_id) VALUES (25, 'ddddddddddddddddddddddddddddddddddd', 'ddddddddddddddddddddddddddddddddddddddddddd', '2023-11-06 21:28:04', 1, 2);
INSERT INTO forum.posts (post_id, title, content, post_timestamp, user_id, category_id) VALUES (26, 'xbdgnsdzklmbg,nhtfdikfmxl,.b;nhfnfd', 'sdjngkf.mv,fdujkflk,gbgdhftdgdth', '2023-11-06 21:29:00', 1, 3);


INSERT INTO comments (user_id, post_id, content,comment_timestamp)
VALUES (1, 1, 'Bitcoin is always safer than ALT coins so trading with bitcoins will have less chance of loss ','2023-06-21 19:12:00'),
       (2, 1, 'Can someone who is a professional trader just go straight to the point pointing out what is needed to make a successful trader to a newbie to learn quickly?','2023-10-20 19:12:06'),
       (4, 3, 'Copy trading has been on Binance many months ago if not years.','2023-10-18 19:12:19'),
       (3, 2, ' I like the balance it has with all markets, although it focuses on cryptos, you can forex, indices, their commissions are very good.','2023-10-21 19:12:22'),
       (4, 4, '"A trader can use a market price to describe the human thought process that underpins a market''s movement."','2023-10-20 19:12:06');


INSERT INTO posts_tags (post_id, tag_id)
VALUES (1, 1),
       (1, 2),
       (3, 3),
       (4, 3),
       (5, 3),
       (2,4),
       (2,5);

INSERT INTO likes (post_id, user_id)
VALUES (1, 2),
       (3, 1),
       (4, 3),
       (5, 3),
       (1,4),
       (2,4),
       (2,5),
       (3,3);
INSERT INTO forum.categories (category_id, category_name) VALUES (1, 'investing');
INSERT INTO forum.categories (category_id, category_name) VALUES (2, 'trading');
INSERT INTO forum.categories (category_id, category_name) VALUES (3, 'discussion');

