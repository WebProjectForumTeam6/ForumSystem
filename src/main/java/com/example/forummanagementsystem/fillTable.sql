INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_admin, profile_picture_url) VALUES (1, 'John', 'Doe', 'johndoe@example.com', 'johndoe', 'password123', 0, 1, 'https://static.vecteezy.com/system/resources/previews/020/765/399/non_2x/default-profile-account-unknown-icon-black-silhouette-free-vector.jpg');
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_admin, profile_picture_url) VALUES (2, 'Alice', 'Smith', 'alicesmith@example.com', 'alicesmith', 'securepass', 1, 0, 'https://media.istockphoto.com/id/1453365227/photo/young-female-architect-standing-in-her-office-and-smiling.webp?b=1&s=170667a&w=0&k=20&c=DRcVODcvZo_fnvjdlX9n4lOk3BxiBd9KVY2gwE8gv0w=');
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_admin, profile_picture_url) VALUES (3, 'Maria', 'Sanchez', 'mariasanchez@example.com', 'mariasanchez', 'maria123', 0, 0, 'https://images.unsplash.com/photo-1664575602554-2087b04935a5?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8d29tYW58ZW58MHx8MHx8fDA%3D');
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_admin, profile_picture_url) VALUES (4, 'Georgi', 'Stoyanov', 'georgistoyanov@example.com', 'georgist', 'georgi1234', 0, 0, 'https://images.unsplash.com/photo-1568602471122-7832951cc4c5?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8bWFufGVufDB8fDB8fHww');
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_admin, profile_picture_url) VALUES (5, 'Berta', 'Morar', 'berta.morar@example.com', 'bertamorar', 'berta123456', 0, 1, 'https://static.vecteezy.com/system/resources/previews/020/765/399/non_2x/default-profile-account-unknown-icon-black-silhouette-free-vector.jpg');
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_admin, profile_picture_url) VALUES (6, 'Serena', 'Hayes', 'serena.hayes@example.com', 'serenaHayes1', 'serena09876', 1, 0, 'https://static.vecteezy.com/system/resources/previews/020/765/399/non_2x/default-profile-account-unknown-icon-black-silhouette-free-vector.jpg');
INSERT INTO forum.users (user_id, first_name, last_name, email, username, password, is_blocked, is_admin, profile_picture_url) VALUES (7, 'Zella', 'Kertzman', 'zella.kertzman@example.com', 'zella.kertzman', '1234zella', 0, 1, 'https://images.unsplash.com/photo-1564564321837-a57b7070ac4f?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8N3x8bWFufGVufDB8fDB8fHww');

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
INSERT INTO forum.posts (post_id, title, content, post_timestamp, user_id, category_id) VALUES (28, 'Unlocking the Future of Crypto', 'Calling all crypto enthusiasts!  This forum is your hub for everything crypto. Dive into discussions on market trends, new tokens, and investment strategies. Connect with a vibrant community, share insights, and explore the potential of blockchain technology. Lets shape the future of crypto together! Join us and be part of the conversation. See you there!', '2023-11-10 15:24:28', 3, 2);
INSERT INTO forum.posts (post_id, title, content, post_timestamp, user_id, category_id) VALUES (29, ' "Exploring the DeFi Revolution: Shaping the Future of Finance"', '"Hey all, who else is captivated by the potential of DeFi in reshaping traditional finance? Lets discuss the impact of decentralized finance on banking, lending, and the wider financial landscape. Share your thoughts on the future of DeFi and its influence on the world economy. ', '2023-11-10 15:31:55', 4, 1);
INSERT INTO forum.posts (post_id, title, content, post_timestamp, user_id, category_id) VALUES (30, 'Discovering the Power of NFTs: Beyond Digital Art', 'Are you curious about the expanding universe of NFTs? Lets unravel their potential beyond art. Discuss how non-fungible tokens are revolutionizing industries like gaming, real estate, and more. Share your insights on the future landscapes NFTs might transform. ', '2023-11-10 15:46:18', 5, 3);
INSERT INTO forum.posts (post_id, title, content, post_timestamp, user_id, category_id) VALUES (31, 'Crypto Adoption: Bridging the Gap for Mainstream Integration', '"Crypto Adoption: Bridging the Gap for Mainstream Integration

How can we accelerate the adoption of cryptocurrencies in everyday life? Share your thoughts on overcoming barriers to mainstream acceptance. Lets discuss strategies to educate, inspire trust, and enhance accessibility for wider crypto integration', '2023-11-10 15:47:55', 5, 3);
INSERT INTO forum.posts (post_id, title, content, post_timestamp, user_id, category_id) VALUES (32, 'Decentralized Governance: The Future of Decision-Making', '
"Decentralized Governance: The Future of Decision-Making

How can decentralized governance reshape our societal structures? Lets dive into discussions about DAOs, decentralized decision-making, and their potential impact on various sectors. Share your insights on the future of governance in a decentralized world. ', '2023-11-10 15:52:10', 7, 3);
INSERT INTO forum.posts (post_id, title, content, post_timestamp, user_id, category_id) VALUES (33, 'Web3 and the Evolution of the Internet', 'Lets explore the metamorphosis from Web 2.0 to Web3. Discuss the potential of blockchain technology, decentralized apps, and the shift towards a more user-centric, secure, and decentralized internet. Share your visions for the future of online interactions and services. ', '2023-11-10 16:05:27', 4, 3);
INSERT INTO forum.posts (post_id, title, content, post_timestamp, user_id, category_id) VALUES (34, 'Crypto in Real-World Applications: Beyond the Digital Realm', 'Lets discuss the practical applications of crypto beyond the digital sphere. From supply chain management to healthcare, explore how blockchain and cryptocurrencies are making a real impact in various industries. Share insights on the innovative use cases and the potential for wider integration. ', '2023-11-10 16:09:01', 1, 3);
INSERT INTO forum.posts (post_id, title, content, post_timestamp, user_id, category_id) VALUES (35, 'Tokenization: Redefining Asset Ownership', 'Join the conversation on tokenization and its impact on ownership. Explore how assets like real estate, art, and intellectual property are being transformed into digital tokens. Discuss the opportunities and challenges in this new era of fractional ownership and increased liquidity', '2023-11-10 16:12:55', 5, 2);
INSERT INTO forum.posts (post_id, title, content, post_timestamp, user_id, category_id) VALUES (36, 'Privacy in the Crypto World: Balancing Transparency and Security', 'Lets tackle the pivotal topic of privacy in the crypto sphere. Discuss the delicate balance between transparency and security in blockchain transactions. Explore the technologies and protocols ensuring privacy while maintaining the integrity of the decentralized ecosystem. Join in to share thoughts on the future of privacy in crypto', '2023-11-10 16:15:39', 3, 3);


INSERT INTO forum.likes (id, post_id, user_id) VALUES (1, 1, 2);
INSERT INTO forum.likes (id, post_id, user_id) VALUES (2, 3, 1);
INSERT INTO forum.likes (id, post_id, user_id) VALUES (3, 4, 3);
INSERT INTO forum.likes (id, post_id, user_id) VALUES (4, 5, 3);
INSERT INTO forum.likes (id, post_id, user_id) VALUES (5, 1, 4);
INSERT INTO forum.likes (id, post_id, user_id) VALUES (6, 2, 4);
INSERT INTO forum.likes (id, post_id, user_id) VALUES (7, 2, 5);
INSERT INTO forum.likes (id, post_id, user_id) VALUES (8, 3, 3);
INSERT INTO forum.likes (id, post_id, user_id) VALUES (12, 5, 1);

INSERT INTO forum.tags (tag_id, content) VALUES (14, 'cryptocommunity');
INSERT INTO forum.tags (tag_id, content) VALUES (15, 'blockchaintech');
INSERT INTO forum.tags (tag_id, content) VALUES (16, 'investmentstrategie');
INSERT INTO forum.tags (tag_id, content) VALUES (17, 'defi');
INSERT INTO forum.tags (tag_id, content) VALUES (18, 'futurefinance');
INSERT INTO forum.tags (tag_id, content) VALUES (19, 'cryptorevolution');
INSERT INTO forum.tags (tag_id, content) VALUES (20, 'nfts');
INSERT INTO forum.tags (tag_id, content) VALUES (21, 'financialinclusion');
INSERT INTO forum.tags (tag_id, content) VALUES (22, 'cryptoadoption');
INSERT INTO forum.tags (tag_id, content) VALUES (23, 'crypto');
INSERT INTO forum.tags (tag_id, content) VALUES (24, 'web3');
INSERT INTO forum.tags (tag_id, content) VALUES (25, 'realworldcrypto');
INSERT INTO forum.tags (tag_id, content) VALUES (26, 'tokenization');
INSERT INTO forum.tags (tag_id, content) VALUES (27, 'cryptoprivacy');


INSERT INTO forum.posts_tags (id, post_id, tag_id) VALUES (12, 28, 14);
INSERT INTO forum.posts_tags (id, post_id, tag_id) VALUES (13, 28, 15);
INSERT INTO forum.posts_tags (id, post_id, tag_id) VALUES (14, 28, 16);
INSERT INTO forum.posts_tags (id, post_id, tag_id) VALUES (15, 29, 17);
INSERT INTO forum.posts_tags (id, post_id, tag_id) VALUES (16, 29, 18);
INSERT INTO forum.posts_tags (id, post_id, tag_id) VALUES (17, 29, 19);
INSERT INTO forum.posts_tags (id, post_id, tag_id) VALUES (18, 30, 20);
INSERT INTO forum.posts_tags (id, post_id, tag_id) VALUES (19, 31, 21);
INSERT INTO forum.posts_tags (id, post_id, tag_id) VALUES (20, 31, 22);
INSERT INTO forum.posts_tags (id, post_id, tag_id) VALUES (21, 32, 23);
INSERT INTO forum.posts_tags (id, post_id, tag_id) VALUES (22, 33, 24);
INSERT INTO forum.posts_tags (id, post_id, tag_id) VALUES (23, 34, 25);
INSERT INTO forum.posts_tags (id, post_id, tag_id) VALUES (24, 35, 26);
INSERT INTO forum.posts_tags (id, post_id, tag_id) VALUES (25, 36, 27);

INSERT INTO forum.comments (comment_id, user_id, post_id, content, comment_timestamp) VALUES (1, 1, 1, 'Bitcoin is always safer than ALT coins so trading with bitcoins will have less chance of loss ', '2023-06-21 19:12:00');
INSERT INTO forum.comments (comment_id, user_id, post_id, content, comment_timestamp) VALUES (2, 2, 1, 'Can someone who is a professional trader just go straight to the point pointing out what is needed to make a successful trader to a newbie to learn quickly?', '2023-10-20 19:12:06');
INSERT INTO forum.comments (comment_id, user_id, post_id, content, comment_timestamp) VALUES (3, 4, 3, 'Copy trading has been on Binance many months ago if not years.', '2023-10-18 19:12:19');
INSERT INTO forum.comments (comment_id, user_id, post_id, content, comment_timestamp) VALUES (4, 3, 2, ' I like the balance it has with all markets, although it focuses on cryptos, you can forex, indices, their commissions are very good.', '2023-10-21 19:12:22');
INSERT INTO forum.comments (comment_id, user_id, post_id, content, comment_timestamp) VALUES (5, 4, 4, '"A trader can use a market price to describe the human thought process that underpins a markets movement."', '2023-10-20 19:12:06');
INSERT INTO forum.comments (comment_id, user_id, post_id, content, comment_timestamp) VALUES (6, 3, 28, 'Excited to connect with fellow crypto aficionados! Lets dive deep into these discussions and ride the waves of the ever-evolving crypto sphere together. ', '2023-11-10 15:26:47');
INSERT INTO forum.comments (comment_id, user_id, post_id, content, comment_timestamp) VALUES (7, 3, 28, 'Im all in for these discussions! From market analysis to the latest token trends, this forums got it all. Looking forward to learning, sharing, and growing together in the crypto world.', '2023-11-10 15:27:26');
INSERT INTO forum.comments (comment_id, user_id, post_id, content, comment_timestamp) VALUES (8, 4, 28, 'Count me in! Love the idea of a space where everyones thoughts count. Lets build a community where diverse perspectives fuel our understanding of the crypto landscape', '2023-11-10 15:29:16');
INSERT INTO forum.comments (comment_id, user_id, post_id, content, comment_timestamp) VALUES (9, 5, 29, 'DeFis impact on banking and lending is monumental. This forum is the ideal space to brainstorm ideas and insights on how this innovation can shape the future of finance. Cant wait to exchange thoughts with you all! ', '2023-11-10 15:33:45');
INSERT INTO forum.comments (comment_id, user_id, post_id, content, comment_timestamp) VALUES (10, 6, 29, 'Absolutely fascinated by the possibilities DeFi offers! Its revolutionary how it challenges conventional financial systems. Cant wait to explore the transformative power it holds', '2023-11-10 15:35:13');
INSERT INTO forum.comments (comment_id, user_id, post_id, content, comment_timestamp) VALUES (11, 1, 29, '"DeFi is the future! The way it democratizes finance is truly groundbreaking. Excited to delve deeper into these discussions and learn from everyones perspectives.', '2023-11-10 15:37:33');
INSERT INTO forum.comments (comment_id, user_id, post_id, content, comment_timestamp) VALUES (12, 1, 31, 'Love this discussion! Accessibility and education are vital to bring crypto to the mainstream. Cant wait to explore ideas on how to make it more inclusive for everyone.', '2023-11-10 15:48:54');
INSERT INTO forum.comments (comment_id, user_id, post_id, content, comment_timestamp) VALUES (13, 7, 31, 'This is the conversation the crypto world needs! Lets explore strategies to bridge the gap and make crypto more user-friendly for the general public. Together, we can drive widespread adoption.', '2023-11-10 15:49:53');
INSERT INTO forum.comments (comment_id, user_id, post_id, content, comment_timestamp) VALUES (14, 7, 30, 'Exciting times ahead as NFTs expand into various sectors! Cant wait to delve into these discussions about their potential to revolutionize the way we interact with assets in the digital space.', '2023-11-10 15:50:41');
INSERT INTO forum.comments (comment_id, user_id, post_id, content, comment_timestamp) VALUES (15, 3, 32, 'The potential of decentralized governance is immense! Its fascinating to think about how DAOs could transform decision-making across industries. Cant wait to explore these possibilities. ', '2023-11-10 15:53:01');
INSERT INTO forum.comments (comment_id, user_id, post_id, content, comment_timestamp) VALUES (16, 1, 32, 'DAOs and decentralized governance are game-changers! Looking forward to discussing their potential to revolutionize how decisions are made, creating more inclusive and transparent systems.', '2023-11-10 15:54:13');
INSERT INTO forum.comments (comment_id, user_id, post_id, content, comment_timestamp) VALUES (17, 1, 33, 'The evolution to Web3 is more than just a technological upgrade; its a paradigm shift in how we interact online. Looking forward to discussing the possibilities and challenges of a decentralized internet. ', '2023-11-10 16:06:06');
INSERT INTO forum.comments (comment_id, user_id, post_id, content, comment_timestamp) VALUES (18, 5, 33, 'The shift to Web3 represents a new era in online interactions. Eager to explore its implications and opportunities. Lets dive deep into this transformative journey together.', '2023-11-10 16:07:01');
INSERT INTO forum.comments (comment_id, user_id, post_id, content, comment_timestamp) VALUES (19, 7, 34, 'Exciting times ahead as crypto ventures into real-world applications! Cant wait to explore how blockchain is revolutionizing diverse industries. Lets uncover these groundbreaking use cases.', '2023-11-10 16:10:40');
INSERT INTO forum.comments (comment_id, user_id, post_id, content, comment_timestamp) VALUES (20, 5, 34, 'Blockchains potential to reshape industries is fascinating! Lets exchange ideas on the innovative applications that are driving this technology into the heart of real-world sectors.', '2023-11-10 16:11:39');
INSERT INTO forum.comments (comment_id, user_id, post_id, content, comment_timestamp) VALUES (21, 1, 35, 'The concept of tokenizing assets is revolutionary! This forum is the perfect space to discuss the opportunities and challenges that come with this new form of ownership. Exciting times ahead!', '2023-11-10 16:13:36');
INSERT INTO forum.comments (comment_id, user_id, post_id, content, comment_timestamp) VALUES (22, 3, 35, 'Tokenization is democratizing ownership! Eager to explore how its reshaping traditional assets into digital tokens, making ownership more accessible and liquid. Lets uncover its implications together. ', '2023-11-10 16:14:20');
INSERT INTO forum.comments (comment_id, user_id, post_id, content, comment_timestamp) VALUES (23, 7, 36, 'Balancing privacy and security is key in the crypto space. Cant wait to discuss the technologies ensuring privacy while maintaining the integrity of blockchain transactions. Lets dive into this complex but vital topic', '2023-11-10 16:17:34');
INSERT INTO forum.comments (comment_id, user_id, post_id, content, comment_timestamp) VALUES (24, 5, 36, 'Protecting privacy without sacrificing security is a critical discussion. Excited to dive into conversations on how the crypto world is navigating this delicate balance. Lets share insights on maintaining privacy in blockchain transactions', '2023-11-10 16:18:21');

