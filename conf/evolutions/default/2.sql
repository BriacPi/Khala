

# --- !Ups
CREATE TABLE IF NOT EXISTS articles_stats (
article_id INT NOT NULL UNIQUE,
nb_views INT NOT NULL DEFAULT 0,
nb_likes INT NOT NULL DEFAULT 0,
nb_comments INT NOT NULL DEFAULT 0,
nb_bookmarks INT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS authors_stats(
author_id INT NOT NULL UNIQUE,
nb_followers INT NOT NULL DEFAULT 0,
nb_articles INT NOT NULL DEFAULT 0
);


CREATE TABLE IF NOT EXISTS tags_stats(
id SERIAL PRIMARY KEY NOT NULL,
tag_name varchar(100) NOT NULL UNIQUE,
nb_articles Int NOT NULL DEFAULT 0
);



create or replace function article_insert() returns trigger
  security definer
  language plpgsql
as $$
  begin
    insert into articles_stats(article_id) values(new.id);

    update authors_stats set nb_articles = nb_articles+1
    where author_id = new.author_id AND new.status != 'draft';

    return new;
  end;
$$;
create trigger article_insert after insert on articles
    for each row execute procedure article_insert();


create or replace function article_delete() returns trigger
  security definer
  language plpgsql
as $$
  begin
    delete from articles_stats where article_id = old.id;
    return new;
  end;
$$;
create trigger article_delete after delete on articles
    for each row execute procedure article_delete();


create or replace function view_insert() returns trigger
  security definer
  language plpgsql
as $$
  begin
    update articles_stats set nb_views = nb_views+1
    where article_id = new.article_id;
    return new;
  end;
$$;
create trigger view_insert after insert on views
    for each row execute procedure view_insert();


create or replace function view_delete() returns trigger
  security definer
  language plpgsql
as $$
  begin
    update articles_stats set nb_views = nb_views-1
    where article_id = old.article_id;
    return new;
  end;
$$;
create trigger view_delete after delete on views
    for each row execute procedure view_delete();


create or replace function like_insert() returns trigger
  security definer
  language plpgsql
as $$
  begin
    update articles_stats set nb_likes = nb_likes+1
    where article_id = new.article_id;
    return new;
  end;
$$;
create trigger like_insert after insert on likes
    for each row execute procedure like_insert();


create or replace function like_delete() returns trigger
  security definer
  language plpgsql
as $$
  begin
    update articles_stats set nb_likes = nb_likes-1
    where article_id = old.article_id;
    return new;
  end;
$$;
create trigger like_delete after delete on likes
    for each row execute procedure like_delete();



create or replace function comment_insert() returns trigger
  security definer
  language plpgsql
as $$
  begin
    update articles_stats set nb_comments = nb_comments+1
    where article_id = new.article_id;
    return new;
  end;
$$;
create trigger comment_insert after insert on comments
    for each row execute procedure comment_insert();


create or replace function comment_delete() returns trigger
  security definer
  language plpgsql
as $$
  begin
    update articles_stats set nb_comments = nb_comments-1
    where article_id = old.article_id;
    return new;
  end;
$$;
create trigger comment_delete after delete on comments
    for each row execute procedure comment_delete();


create or replace function bookmark_insert() returns trigger
  security definer
  language plpgsql
as $$
  begin
    update articles_stats set nb_bookmarks = nb_bookmarks+1
    where article_id = new.article_id;
    return new;
  end;
$$;
create trigger bookmark_insert after insert on bookmarks
    for each row execute procedure bookmark_insert();


create or replace function bookmark_delete() returns trigger
  security definer
  language plpgsql
as $$
  begin
    update articles_stats set nb_bookmarks = nb_bookmarks-1
    where article_id = old.article_id;
    return new;
  end;
$$;
create trigger bookmark_delete after delete on bookmarks
    for each row execute procedure bookmark_delete();









create or replace function article_update() returns trigger 
security definer
  language plpgsql
as $$
  begin
    update authors_stats set nb_articles = nb_articles+1
    where author_id = new.author_id AND old.status = 'draft' AND new.status !='draft';
    return new;
  end;
$$;
create trigger article_update after update on articles
    for each row execute procedure article_update();


create or replace function author_insert() returns trigger
  security definer
  language plpgsql
as $$
  begin
    insert into authors_stats(author_id) values(new.id);
    return new;
  end;
$$;
create trigger author_insert after insert on users
    for each row execute procedure author_insert();


create or replace function author_delete() returns trigger
  security definer
  language plpgsql
as $$
  begin
    delete from authors_stats where author_id = old.id;
    return new;
  end;
$$;
create trigger author_delete after delete on users
    for each row execute procedure author_delete();

create or replace function follower_insert() returns trigger
  security definer
  language plpgsql
as $$
  begin
    update authors_stats set nb_followers = nb_followers+1
    where author_id = new.author_id;
    return new;
  end;
$$;
create trigger follower_insert after insert on follows
    for each row execute procedure follower_insert();


create or replace function follower_delete() returns trigger
  security definer
  language plpgsql
as $$
  begin
    update authors_stats set nb_followers = nb_followers-1
    where author_id = old.author_id;
    return new;
  end;
$$;
create trigger follower_delete after delete on follows
    for each row execute procedure follower_delete();







create or replace function tag_insert() returns trigger
  security definer
  language plpgsql
as $$
  begin
    insert into tags_stats(tag_name) values(new.name);
    return new;
  end;
$$;
create trigger tag_insert after insert on tags
    for each row execute procedure tag_insert();


create or replace function tag_delete() returns trigger
  security definer
  language plpgsql
as $$
  begin
    delete from tags_stats where tag_name = old.name;
    return new;
  end;
$$;
create trigger tag_delete after delete on tags
    for each row execute procedure tag_delete();



create or replace function tagging_insert() returns trigger
  security definer
  language plpgsql
as $$
  begin
    update tags_stats set nb_articles = nb_articles+1
    where tag_name = new.tag_name;
    return new;
  end;
$$;
create trigger tagging_insert after insert on taggings
    for each row execute procedure tagging_insert();


create or replace function tagging_delete() returns trigger
  security definer
  language plpgsql
as $$
  begin
    delete from tags_stats where tag_name = old.tag_name;
    return new;
  end;
$$;
create trigger tagging_delete after delete on taggings
    for each row execute procedure tagging_delete();





# --- !Downs


DROP FUNCTION IF EXISTS article_insert();
DROP FUNCTION IF EXISTS article_delete();
DROP FUNCTION IF EXISTS view_insert();
DROP FUNCTION IF EXISTS view_delete();
DROP FUNCTION IF EXISTS like_insert();
DROP FUNCTION IF EXISTS like_delete();
DROP FUNCTION IF EXISTS comment_insert();
DROP FUNCTION IF EXISTS comment_delete();
DROP FUNCTION IF EXISTS bookmark_insert();
DROP FUNCTION IF EXISTS bookmark_delete();
DELETE FROM pg_trigger;