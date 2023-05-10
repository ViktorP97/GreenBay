SELECT users_name, item_photo_url
FROM items
INNER JOIN users ON user_id = seller_id;

SELECT SUM(id) FROM users;

DELETE FROM users WHERE id=1;
INSERT INTO users VALUES(id,"name", "password", "email");
INSERT INTO user (id , name , email) VALUES (id,"name", "email");
UPDATE users SET name = "Lucka" WHERE id = 1;