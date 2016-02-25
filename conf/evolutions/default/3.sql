
# --- !Ups

CREATE TABLE IF NOT EXISTS publications(
ID SERIAL PRIMARY KEY NOT NULL,
NAME varchar(100) NOT NULL UNIQUE,
creator_id INT NOT NULL,
creation_date TIMESTAMP NOT NULL
);

create or replace function publication_insert() returns trigger
  security definer
  language plpgsql
as $$
  begin
    INSERT INTO publications_stats(publication_id) VALUES(new.id);
    return new;
  end;
$$;
create trigger publication_insert after insert on publications
    for each row execute procedure publication_insert();


create or replace function publication_delete() returns trigger
  security definer
  language plpgsql
as $$
  begin
    delete from publications_stats where publication_id = old.id;
    return new;
  end;
$$;
create trigger publication_delete after delete on publications
    for each row execute procedure publication_delete();



CREATE TABLE IF NOT EXISTS publication_members(
publication_name varchar(100) NOT NULL,
member_id INT NOT NULL,
join_date TIMESTAMP NOT NULL,
title VARCHAR(25) NOT NULL
);

create or replace function publication_member_insert() returns trigger
  security definer
  language plpgsql
as $$
  begin
    UPDATE publications_stats SET nb_memebers = nb_members+1;
    return new;
  end;
$$;
create trigger publication_member_insert after insert on publication_members
    for each row execute procedure publication_member_insert();


create or replace function publication_member_delete() returns trigger
  security definer
  language plpgsql
as $$
  begin
  UPDATE publications_stats SET nb_memebers = nb_members-1;
    return new;
  end;
$$;
create trigger publication_member_delete after delete on publication_members
    for each row execute procedure publication_member_delete();




CREATE TABLE IF NOT EXISTS publication_followers(
publication_name varchar(100) NOT NULL,
follower_id INT NOT NULL,
follow_date TIMESTAMP NOT NULL
);

create or replace function publication_follower_insert() returns trigger
  security definer
  language plpgsql
as $$
  begin
    UPDATE publications_stats SET nb_followers = nb_followers+1;
    return new;
  end;
$$;
create trigger publication_follower_insert after insert on publication_followers
    for each row execute procedure publication_follower_insert();


create or replace function publication_follower_delete() returns trigger
  security definer
  language plpgsql
as $$
  begin
  UPDATE publications_stats SET nb_followers = nb_followers-1;
    return new;
  end;
$$;
create trigger publication_follower_delete after delete on publication_followers
    for each row execute procedure publication_follower_delete();




CREATE TABLE IF NOT EXISTS publication_articles(
publication_name varchar(100) NOT NULL,
article_id INT NOT NULL,
proposed_by INT NOT NULL,
proposed_date TIMESTAMP NOT NULL,
state_of_article TIMESTAMP NOT NULL,
validated_date TIMESTAMP
);


create or replace function publication_article_insert() returns trigger
  security definer
  language plpgsql
as $$
  begin
    UPDATE publications_stats SET nb_articles = nb_articles+1;
    return new;
  end;
$$;
create trigger publication_article_insert after insert on publication_articles
    for each row execute procedure publication_article_insert();


create or replace function publication_article_delete() returns trigger
  security definer
  language plpgsql
as $$
  begin
  UPDATE publications_stats SET nb_articles = nb_articles-1;
    return new;
  end;
$$;
create trigger  publication_article_delete after delete on  publication_articles
    for each row execute procedure publication_article_delete();


CREATE TABLE IF NOT EXISTS publications_stats(
publication_id INT NOT NULL UNIQUE,
nb_members INT NOT NULL DEFAULT 0,
nb_followers INT NOT NULL DEFAULT 0,
nb_articles INT NOT NULL DEFAULT 0
);






# --- !Downs


DROP TABLE publications CASCADE;
DROP TABLE publication_members CASCADE
DROP TABLE publications_followers CASCADE
DROP TABLE publications_articles CASCADE
DROP TABLE publications_stats CASCADE
