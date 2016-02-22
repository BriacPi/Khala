//package repositories
//
//import anorm.SqlParser._
//import anorm._
//import models.{AuthorFollowers, AuthorViews, AuthorLikes}
//import play.api.db.DB
//
//import play.api.Play.current
///**
//  * Created by corpus on 22/02/2016.
//  */
//trait MyProfileRepository {
//  private[repositories] val recordMapperAuthorFollowers = {
//    long("author_id") ~
//      date("follow_date") ~
//      int("nb_followers") ~
//      int("commulative_nb_followers") ~
//      int("max_nb_followers") map {
//      case id ~ follow_date ~ nb_followers ~ commulative_nb_followers ~ max_nb_followers => {
//        AuthorFollowers(id, follow_date, nb_followers, commulative_nb_followers, max_nb_followers)
//      }
//    }
//  }
//
//  private[repositories] val recordMapperAuthorViews = {
//    long("author_id") ~
//      date("view_date") ~
//      int("nb_views") ~
//      int("commulative_nb_views") ~
//      int("max_nb_views") map {
//      case id ~ view_date ~ nb_views ~ commulative_nb_views ~ max_nb_views => {
//        AuthorViews(id, view_date, nb_views, commulative_nb_views, max_nb_views)
//      }
//    }
//  }
//
//  private[repositories] val recordMapperAuthorLikes = {
//    long("author_id") ~
//      date("like_date") ~
//      int("nb_likes") ~
//      int("commulative_nb_likes") ~
//      int("max_nb_likes") map {
//      case id ~ like_date ~ nb_likes ~ commulative_nb_likes ~ max_nb_likes => {
//        AuthorLikes(id, like_date, nb_likes, commulative_nb_likes, max_nb_likes)
//      }
//    }
//  }
//}
//
//object MyProfileRepository extends MyProfileRepository {
//  def getStatsFollowers(authorId: Long): List[AuthorFollowers] = {
//
//    DB.withConnection { implicit current =>
//      SQL(
//        """
//          CREATE TEMPORARY VIEW {name_temp_view}
//          AS (SELECT users.id AS author_id, Date(follows.follow_date) AS follow_date, COUNT(follows.id) as nb_followers
//          FROM users
//          LEFT JOIN follows ON users.id = follows.author_id
//          WHERE users.id = {authorId}
//          GROUP BY users.id, Date(follows.follow_date)
//          );
//          Select t1.author_id AS author_id, t1.follow_date AS follow_date,
//          t1.nb_followers AS nb_followers, SUM(t2.nb_followers) as commulative_nb_followers,
//          MAX(t2.nb_followers) as max_nb_followers
//          FROM {name_temp_view}as t1
//          INNER JOIN nb_followers_userId as t2 on t1.follow_date>=t2.follow_date AND t1.author_id = t2.author_id
//          GROUP BY t1.author_id,t1.follow_date,t1.nb_followers
//          ORDER BY t1.follow_date;
//        """
//      )
//        .on(
//          "name_temp_view" -> ("nb_followers_"+authorId.toString),
//          "authorId" -> authorId
//        )
//        .as(recordMapperAuthorFollowers *)
//        .toList
//    }
//  }
//
//  def getStatsViews(authorId: Long): List[AuthorViews] = {
//    DB.withConnection { implicit current =>
//      SQL(
//        """
//          |CREATE TEMPORARY VIEW {name_temp_view}
//          |AS (SELECT users.id AS author_id, Date(views.view_date) as view_date, COUNT(views.id) as nb_views
//          |FROM users
//          |LEFT JOIN articles ON users.id = articles.author_id
//          |LEFT JOIN views ON articles.id = views.article_id
//          |WHERE users.id = {authorId}
//          |GROUP BY users.id, Date(views.view_date)
//          |);
//          |Select t1.author_id AS author_id, t1.view_date AS view_date,
//          |t1.nb_views AS nb_views,  SUM(t2.nb_views) AS commulative_nb_views, MAX(t2.nb_views) AS max_nb_views
//          |FROM {name_temp_view} AS t1
//          |INNER JOIN nb_views_userId AS t2 on t1.view_date>=t2.view_date
//          |GROUP BY t1.author_id,t1.view_date
//          |ORDER BY t1.view_date;
//        """
//      )
//        .on(
//          "name_temp_view" -> ("nb_views_"+authorId.toString()),
//          "authorId" -> authorId
//        )
//        .as(recordMapperAuthorViews *)
//        .toList
//    }
//  }
//
//  def getStatsLikes(authorId: Long): List[AuthorLikes] = {
//    DB.withConnection { implicit current =>
//      SQL(
//        """
//          |CREATE TEMPORARY VIEW {name_temp_view}
//          |(SELECT users.id AS author_id, Date(likes.like_date) as like_date, COUNT(likes.id) as nb_likes
//          |FROM users
//          |LEFT JOIN articles ON users.id = articles.author_id
//          |LEFT JOIN views ON articles.id = likes.article_id
//          |WHERE users.id = {authorId}
//          |GROUP BY users.id, Date(likes.like_date)
//          |)
//          |Select t1.author_id AS author_id, t1.like_date  AS like_date,
//          |t1.nb_likes AS nb_likes, SUM(t2.nb_likes) AS commulative_nb_likes,  MAX(t2.nb_likes) AS max_nb_likes
//          |FROM {name_temp_view} AS t1
//          |INNER JOIN nb_likes_userId AS t2 on t1.like_date>=t2.like_date
//          |GROUP BY t1.author_id,t1.like_date
//          |ORDER BY t1.like_date;
//        """
//      )
//        .on(
//          "name_temp_view" -> ("nb_likes_"+authorId.toString()),
//          "authorId" -> authorId
//        )
//        .as(recordMapperAuthorLikes *)
//        .toList
//    }
//  }
//
//}