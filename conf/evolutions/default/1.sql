
# --- !Ups
CREATE TABLE IF NOT EXISTS USERS(
ID SERIAL PRIMARY KEY NOT NULL,
FIRST_NAME varchar(100) NOT NULL,
LAST_NAME  varchar(100) NOT NULL,
EMAIL varchar(255) NOT NULL UNIQUE,
EMAIL_CONFIRMED   BOOLEAN NOT NULL,
PASSWORD  varchar(255) NOT NULL,
HEADLINE varchar(150),
BIO  varchar(1500),
URL_PHOTO_PROFILE TEXT NOT NULL,
SERVICES varchar(50) NOT NULL,
REGISTRATION_DATE  timestamp NOT NULL
);

CREATE TABLE IF NOT EXISTS ARTICLES(
ID SERIAL PRIMARY KEY NOT NULL,
AUTHOR_ID INT NOT NULL,
CREATION_DATE timestamp NOT NULL,
LAST_UPDATE timestamp NOT NULL,
TITLE  TEXT NOT NULL,
SUMMARY TEXT,
CONTENT  TEXT NOT NULL,
NB_MODIFICATIONS  INT NOT NULL,
READING_TIME  INT NOT NULL,
TAG1 varchar(100) NOT NULL,
TAG2 varchar(100),
STATUS varchar(50) NOT NULL DEFAULT 'draft'
);

CREATE TABLE IF NOT EXISTS COMMENTS(
ID SERIAL PRIMARY KEY NOT NULL,
USER_ID INT NOT NULL,
PARENT_ID INT NOT NULL,
PARENT_TYPE VARCHAR(25) NOT NULL,
CREATION_DATE timestamp NOT NULL,
LAST_UPDATE timestamp NOT NULL,
CONTENT TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS VIEWS(
ID SERIAL PRIMARY KEY NOT NULL,
USER_ID INT NOT NULL,
AUTHOR_ID INT NOT NULL,
ARTICLE_ID INT NOT NULL,
VIEW_DATE timestamp NOT NULL
);

CREATE TABLE IF NOT EXISTS LIKES(
ID SERIAL PRIMARY KEY NOT NULL,
USER_ID INT NOT NULL,
AUTHOR_ID INT NOT NULL,
ARTICLE_ID INT NOT NULL,
LIKE_DATE timestamp NOT NULL
);

CREATE TABLE IF NOT EXISTS COMMENT_LIKES(
ID SERIAL PRIMARY KEY NOT NULL,
USER_ID INT NOT NULL,
AUTHOR_ID INT NOT NULL,
COMMENT_ID INT NOT NULL,
LIKE_DATE timestamp NOT NULL
);

CREATE TABLE IF NOT EXISTS tags(
ID SERIAL PRIMARY KEY NOT NULL,
NAME varchar(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS taggings(
ID SERIAL PRIMARY KEY NOT NULL,
TAG_NAME varchar(100) NOT NULL,
AUTHOR_ID INT NOT NULL,
ARTICLE_ID INT NOT NULL,
TAGGING_DATE  timestamp NOT NULL
);

CREATE TABLE IF NOT EXISTS interests(
ID SERIAL PRIMARY KEY NOT NULL,
tag_name INT NOT NULL,
user_id INT NOT NULL,
interest_date timestamp NOT NULL
);


CREATE TABLE IF NOT EXISTS follows(
ID SERIAL PRIMARY KEY NOT NULL,
FOLLOWER_ID INT NOT NULL,
AUTHOR_ID INT NOT NULL,
FOLLOW_DATE timestamp NOT NULL
);

CREATE TABLE IF NOT EXISTS bookmarks(
ID SERIAL PRIMARY KEY NOT NULL,
USER_ID INT NOT NULL,
AUTHOR_ID INT NOT NULL,
ARTICLE_ID INT NOT NULL,
BOOKMARK_DATE timestamp NOT NULL
);


CREATE TABLE IF NOT EXISTS articles_stats(
article_id INT NOT NULL UNIQUE,
nb_views INT NOT NULL DEFAULT 0,
nb_likes INT NOT NULL DEFAULT 0,
nb_comments INT NOT NULL DEFAULT 0,
nb_bookmarks INT NOT NULL DEFAULT 0
);


CREATE TABLE IF NOT EXISTS authors_stats(
author_id INT NOT NULL UNIQUE,
nb_followers INT NOT NULL DEFAULT 0,
nb_articles INT NOT NULL DEFAULT 0,
nb_views INT NOT NULL DEFAULT 0,
nb_likes INT NOT NULL DEFAULT 0,
nb_bookmarks INT NOT NULL DEFAULT 0
);


CREATE TABLE IF NOT EXISTS tags_stats(
id SERIAL PRIMARY KEY NOT NULL,
tag_name varchar(100) NOT NULL UNIQUE,
nb_articles Int NOT NULL DEFAULT 0,
nb_interested_users Int NOT NULL DEFAULT 0
); 

CREATE TABLE IF NOT EXISTS comments_stats(
comment_id INT NOT NULL UNIQUE,
nb_likes INT NOT NULL DEFAULT 0,
nb_comments INT NOT NULL DEFAULT 0
);

# --- !Downs


DROP TABLE USERS CASCADE;
DROP TABLE ARTICLES CASCADE;
DROP TABLE COMMENTS CASCADE;
DROP TABLE VIEWS CASCADE;
DROP TABLE LIKES CASCADE;
DROP TABLE TAGS CASCADE;
DROP TABLE TAGGINGS CASCADE;
DROP TABLE FOLLOWS CASCADE;
DROP TABLE BOOKMARKS CASCADE;
DROP TABLE articles_stats CASCADE;
DROP TABLE authors_stats CASCADE;
DROP TABLE tags_stats CASCADE;
