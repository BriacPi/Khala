
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
TAG2 varchar(100)
);

CREATE TABLE IF NOT EXISTS COMMENTS(
ID SERIAL PRIMARY KEY NOT NULL,
USER_ID INT NOT NULL,
ARTICLE_ID INT NOT NULL,
CREATION_DATE timestamp NOT NULL,
LAST_UPDATE timestamp NOT NULL,
CONTENT TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS VIEWS(
ID SERIAL PRIMARY KEY NOT NULL,
USER_ID INT NOT NULL,
ARTICLE_ID INT NOT NULL,
VIEW_DATE timestamp NOT NULL
);

CREATE TABLE IF NOT EXISTS LIKES(
ID SERIAL PRIMARY KEY NOT NULL,
USER_ID INT NOT NULL,
ARTICLE_ID INT NOT NULL,
LIKE_DATE timestamp NOT NULL
);


CREATE TABLE IF NOT EXISTS tags(
ID SERIAL PRIMARY KEY NOT NULL,
NAME varchar(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS taggings(
ID SERIAL PRIMARY KEY NOT NULL,
TAG_NAME varchar(100) NOT NULL,
ARTICLE_ID INT NOT NULL,
TAGGING_DATE  timestamp NOT NULL
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
ARTICLE_ID INT NOT NULL,
BOOKMARK_DATE timestamp NOT NULL
);


CREATE MATERIALIZED VIEW IF NOT EXISTS articles_views AS(
SELECT articles.id AS article_id,
COUNT(views.id) AS nb_views
FROM articles 
LEFT JOIN views ON articles.id = views.article_id
GROUP BY articles.id
);
CREATE UNIQUE INDEX articles_views_index ON articles_views(article_id);

CREATE MATERIALIZED VIEW IF NOT EXISTS articles_likes AS(
SELECT articles.id AS article_id,
COUNT(likes.id) AS nb_likes
FROM articles 
LEFT JOIN likes ON articles.id = likes.article_id
GROUP BY articles.id
);
CREATE UNIQUE INDEX articles_likes_index ON articles_likes(article_id);

CREATE MATERIALIZED VIEW IF NOT EXISTS articles_comments AS(
SELECT articles.id AS article_id,
COUNT(comments.id) AS nb_comments
FROM articles 
LEFT JOIN comments ON articles.id = comments.article_id
GROUP BY articles.id
);
CREATE UNIQUE INDEX articles_comments_index ON articles_comments(article_id);

CREATE MATERIALIZED VIEW IF NOT EXISTS articles_bookmarks AS(
SELECT articles.id AS article_id,
COUNT(bookmarks.id) AS nb_bookmarks
FROM articles 
LEFT JOIN bookmarks ON articles.id = bookmarks.article_id
GROUP BY articles.id
);
CREATE UNIQUE INDEX articles_bookmarks_index ON articles_bookmarks(article_id);

CREATE MATERIALIZED VIEW IF NOT EXISTS authors_followers AS(
SELECT users.id AS author_id,
COUNT(follows.id) AS nb_followers
FROM users 
LEFT JOIN follows ON users.id = follows.author_id
GROUP BY users.id
);
CREATE UNIQUE INDEX authors_followers_index ON authors_followers(author_id);

CREATE MATERIALIZED VIEW IF NOT EXISTS authors_articles AS(
SELECT users.id AS author_id,
COUNT(articles.id) AS nb_articles
FROM users 
LEFT JOIN articles ON users.id = articles.author_id
GROUP BY users.id
);
CREATE UNIQUE INDEX authors_articles_index ON authors_articles(author_id);


CREATE MATERIALIZED VIEW IF NOT EXISTS tags_stats AS(
SELECT tags.name AS tag_name,
COUNT(DISTINCT taggings.article_id) AS nb_articles,
SUM(articles_views.nb_views) as nb_views_total
FROM tags 
LEFT JOIN taggings ON tags.name = taggings.tag_name
LEFT JOIN articles_views ON taggings.article_id = articles_views.article_id
GROUP BY tags.name
);
CREATE UNIQUE INDEX tags_stats_index ON tags_stats(tag_name);

REFRESH MATERIALIZED VIEW CONCURRENTLY articles_views;
REFRESH MATERIALIZED VIEW CONCURRENTLY articles_likes;
REFRESH MATERIALIZED VIEW CONCURRENTLY articles_comments;
REFRESH MATERIALIZED VIEW CONCURRENTLY articles_bookmarks;
REFRESH MATERIALIZED VIEW CONCURRENTLY authors_followers;
REFRESH MATERIALIZED VIEW CONCURRENTLY authors_articles;
REFRESH MATERIALIZED VIEW CONCURRENTLY tags_stats;

# --- !Downs

DROP MATERIALIZED VIEW articles_views CASCADE;
DROP MATERIALIZED VIEW articles_likes CASCADE; 
DROP MATERIALIZED VIEW articles_comments CASCADE;
DROP MATERIALIZED VIEW articles_bookmarks CASCADE;
DROP MATERIALIZED VIEW authors_followers CASCADE;
DROP MATERIALIZED VIEW authors_articles CASCADE;
DROP MATERIALIZED VIEW tags_stats CASCADE;
DROP TABLE USERS CASCADE;
DROP TABLE ARTICLES CASCADE;
DROP TABLE COMMENTS CASCADE;
DROP TABLE VIEWS CASCADE;
DROP TABLE LIKES CASCADE;
DROP TABLE TAGS CASCADE;
DROP TABLE TAGGINGS CASCADE;
DROP TABLE FOLLOWS CASCADE;
DROP TABLE BOOKMARKS CASCADE;
