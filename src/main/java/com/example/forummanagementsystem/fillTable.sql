INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_admin, profile_picture_url) VALUES (1, 'John', 'Doe', 'johndoe@example.com', 'johndoe', 'password123', 0, 1, 'https://static.vecteezy.com/system/resources/previews/020/765/399/non_2x/default-profile-account-unknown-icon-black-silhouette-free-vector.jpg');
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_admin, profile_picture_url) VALUES (2, 'Alice', 'Smith', 'alicesmith@example.com', 'alicesmith', 'securepass', 1, 0, 'https://media.istockphoto.com/id/1453365227/photo/young-female-architect-standing-in-her-office-and-smiling.webp?b=1&s=170667a&w=0&k=20&c=DRcVODcvZo_fnvjdlX9n4lOk3BxiBd9KVY2gwE8gv0w=');
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_admin, profile_picture_url) VALUES (3, 'Maria', 'Sanchez', 'mariasanchez@example.com', 'mariasanchez', 'maria123', 0, 0, 'https://images.unsplash.com/photo-1664575602554-2087b04935a5?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8d29tYW58ZW58MHx8MHx8fDA%3D');
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_admin, profile_picture_url) VALUES (4, 'Georgi', 'Stoyanov', 'georgistoyanov@example.com', 'georgist', 'georgi1234', 0, 0, 'https://images.unsplash.com/photo-1568602471122-7832951cc4c5?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWFufGVufDB8fDB8fHww');
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_admin, profile_picture_url) VALUES (5, 'Berta', 'Morar', 'berta.morar@example.com', 'bertamorar', 'berta123456', 0, 1, 'https://static.vecteezy.com/system/resources/previews/020/765/399/non_2x/default-profile-account-unknown-icon-black-silhouette-free-vector.jpg');
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_admin, profile_picture_url) VALUES (6, 'Serena', 'Hayes', 'serena.hayes@example.com', 'serenaHayes1', 'serena09876', 1, 0, 'https://static.vecteezy.com/system/resources/previews/020/765/399/non_2x/default-profile-account-unknown-icon-black-silhouette-free-vector.jpg');
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_admin, profile_picture_url) VALUES (7, 'Zella', 'Kertzman', 'zella.kertzman@example.com', 'zella.kertzman', '1234zella', 0, 1, 'https://images.unsplash.com/photo-1564564321837-a57b7070ac4f?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8N3x8bWFufGVufDB8fDB8fHww');
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_admin, profile_picture_url) VALUES (11, 'fefwefwefe3wvr3', 'ddddddddddd', 'ddddddddddddd@abv.bg', 'ddddddddddd', 'ddddddddddd', 0, 0, 'https://static.vecteezy.com/system/resources/previews/020/765/399/non_2x/default-profile-account-unknown-icon-black-silhouette-free-vector.jpg');
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_admin, profile_picture_url) VALUES (12, 'ddddddddddd', 'ddddddddddd', 'ddddddddddddd@abv.bg', 'ddddddddddddddd', 'ddddddddddd', 0, 0, 'https://static.vecteezy.com/system/resources/previews/020/765/399/non_2x/default-profile-account-unknown-icon-black-silhouette-free-vector.jpg');

INSERT INTO forum.admins_info (admin_id, user_id, phone_number) VALUES (1, 1, '1234567890');
INSERT INTO forum.admins_info (admin_id, user_id, phone_number) VALUES (2, 5, '9876543210');
INSERT INTO forum.admins_info (admin_id, user_id, phone_number) VALUES (3, 7, '1245789552');

INSERT INTO forum.categories (category_id, category_name) VALUES (1, 'investing');
INSERT INTO forum.categories (category_id, category_name) VALUES (2, 'trading');
INSERT INTO forum.categories (category_id, category_name) VALUES (3, 'discussion');

INSERT INTO forum.posts (post_id, title, content, post_timestamp, user_id, category_id) VALUES (1, 'Trade only Bitcoin', 'Trading is a lifetime skill that is always beneficial if you know what you are doing, the biggest mistake of traders is trading alt that most times does not obey market structure,
                                                                                                   when it comes to cryptocurrency, trade only Bitcoin.', '2023-05-01 19:09:24', 1, 1);
INSERT INTO forum.posts (post_id, title, content, post_timestamp, user_id, category_id) VALUES (2, 'How long to learn trading?', 'Do you think that trading can be learnt well in a short time like three weeks for a quick trader?', '2023-07-24 19:09:14', 2, 1);
INSERT INTO forum.posts (post_id, title, content, post_timestamp, user_id, category_id) VALUES (3, 'Binance is Rolling Out Copy Trading?', 'did Binance just announce they are finally jumping on the copy trading feature?', '2023-10-21 19:09:10', 3, 1);
INSERT INTO forum.posts (post_id, title, content, post_timestamp, user_id, category_id) VALUES (4, 'App for crypto indicators', 'I try to find some apps for indicators. ', '2023-07-24 19:09:14', 4, 1);
INSERT INTO forum.posts (post_id, title, content, post_timestamp, user_id, category_id) VALUES (5, 'For funded traders / my top 3 prop firm', '1-Crypto fund trader 2-Funded next 3-The Funded Trader', '2023-10-21 19:09:10', 2, 1);
INSERT INTO forum.posts (post_id, title, content, post_timestamp, user_id, category_id) VALUES (27, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx', 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx', '2023-11-07 15:39:53', 1, 1);

INSERT INTO forum.likes (id, post_id, user_id) VALUES (1, 1, 2);
INSERT INTO forum.likes (id, post_id, user_id) VALUES (2, 3, 1);
INSERT INTO forum.likes (id, post_id, user_id) VALUES (3, 4, 3);
INSERT INTO forum.likes (id, post_id, user_id) VALUES (4, 5, 3);
INSERT INTO forum.likes (id, post_id, user_id) VALUES (5, 1, 4);
INSERT INTO forum.likes (id, post_id, user_id) VALUES (6, 2, 4);
INSERT INTO forum.likes (id, post_id, user_id) VALUES (7, 2, 5);
INSERT INTO forum.likes (id, post_id, user_id) VALUES (8, 3, 3);
INSERT INTO forum.likes (id, post_id, user_id) VALUES (12, 5, 1);

INSERT INTO forum.tags (tag_id, content) VALUES (1, 'bitcoin');
INSERT INTO forum.tags (tag_id, content) VALUES (2, 'trade');
INSERT INTO forum.tags (tag_id, content) VALUES (3, 'app');
INSERT INTO forum.tags (tag_id, content) VALUES (4, 'crypto');
INSERT INTO forum.tags (tag_id, content) VALUES (5, 'market');
INSERT INTO forum.tags (tag_id, content) VALUES (7, 'audi');
INSERT INTO forum.tags (tag_id, content) VALUES (10, 'mercedess');
INSERT INTO forum.tags (tag_id, content) VALUES (11, 'bmw');
INSERT INTO forum.tags (tag_id, content) VALUES (12, 'peugeot');
INSERT INTO forum.tags (tag_id, content) VALUES (13, 'bmw. toyota');

INSERT INTO forum.posts_tags (id, post_id, tag_id) VALUES (1, 1, 1);
INSERT INTO forum.posts_tags (id, post_id, tag_id) VALUES (2, 1, 2);
INSERT INTO forum.posts_tags (id, post_id, tag_id) VALUES (3, 3, 3);
INSERT INTO forum.posts_tags (id, post_id, tag_id) VALUES (4, 4, 3);
INSERT INTO forum.posts_tags (id, post_id, tag_id) VALUES (5, 5, 3);
INSERT INTO forum.posts_tags (id, post_id, tag_id) VALUES (6, 2, 4);
INSERT INTO forum.posts_tags (id, post_id, tag_id) VALUES (7, 2, 5);
INSERT INTO forum.posts_tags (id, post_id, tag_id) VALUES (9, 1, 10);
INSERT INTO forum.posts_tags (id, post_id, tag_id) VALUES (10, 1, 11);
INSERT INTO forum.posts_tags (id, post_id, tag_id) VALUES (11, 1, 12);

INSERT INTO forum.comments (comment_id, user_id, post_id, content, comment_timestamp) VALUES (1, 1, 1, 'Bitcoin is always safer than ALT coins so trading with bitcoins will have less chance of loss ', '2023-06-21 19:12:00');
INSERT INTO forum.comments (comment_id, user_id, post_id, content, comment_timestamp) VALUES (2, 2, 1, 'Can someone who is a professional trader just go straight to the point pointing out what is needed to make a successful trader to a newbie to learn quickly?', '2023-10-20 19:12:06');
INSERT INTO forum.comments (comment_id, user_id, post_id, content, comment_timestamp) VALUES (3, 4, 3, 'Copy trading has been on Binance many months ago if not years.', '2023-10-18 19:12:19');
INSERT INTO forum.comments (comment_id, user_id, post_id, content, comment_timestamp) VALUES (4, 3, 2, ' I like the balance it has with all markets, although it focuses on cryptos, you can forex, indices, their commissions are very good.', '2023-10-21 19:12:22');
INSERT INTO forum.comments (comment_id, user_id, post_id, content, comment_timestamp) VALUES (5, 4, 4, '"A trader can use a market price to describe the human thought process that underpins a markets movement."', '2023-10-20 19:12:06');



