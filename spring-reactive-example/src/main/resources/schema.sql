CREATE TABLE IF NOT EXISTS post (
    id SERIAL PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    content TEXT,
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- GMT+1 summertime
--SET time_zone='+02:00';