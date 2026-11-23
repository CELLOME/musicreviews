CREATE TABLE IF NOT EXISTS users (
name VARCHAR(128) NOT NULL PRIMARY KEY,
email VARCHAR(254) NOT NULL,
password VARCHAR(255) NOT NULL,
creation_date DATE NOT NULL,
privilege INT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS reviews (
revID INT NOT NULL PRIMARY KEY,
review_title varchar(128),
album_title varchar(128),
artist_name varchar(32),
genres varchar (32),
photo_filename varchar(128),
release_date date,
version_information decimal(10,2),
review_body text,
album_rating varchar(8),
number_of_stars decimal(2,1)
);