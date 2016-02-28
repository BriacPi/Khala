

# --- !Ups
--  This one is for triggers on 1.SQL

CREATE MATERIALIZED VIEW authors_views AS(
SELECT users.id AS author_id, DATE(views_aggregated.view_date) as view_date,
COALESCE(SUM( views_aggregated.nbViews),0) AS nbViews
FROM users
LEFT JOIN articles ON users.id = articles.author_id
LEFT JOIN (
SELECT views.article_id AS article_id, DATE(views.view_date) AS view_date, COUNT(views.id) AS nbViews
FROM views
GROUP by views.article_id, DATE(views.view_date)
) AS views_aggregated
ON articles.id = views_aggregated.article_id
GROUP BY users.id, DATE(views_aggregated.view_date)
);
Create UNIQUE index author_views_index on authors_views (author_id);
# --- !Downs

DROP MATERIALIZED VIEW authors_views;
-- REFRESH MATERIALIZED VIEW CONCURRENTLY authors_views;
