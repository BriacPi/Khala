package repositories.Relationships

import java.sql.Timestamp

import anorm.SqlParser._
import anorm._
import org.joda.time.DateTime
import play.api.Play.current
import play.api.db.DB
import repositories.Contents.ArticleRepository

/**
  * Created by corpus on 21/02/2016.
  */
trait BookmarkRepository {
  private[repositories] val recordMapperId = {
    long("bookmarks.id") map {
      case id => {
        id
      }
    }
  }
}

object BookmarkRepository extends BookmarkRepository {

  def bookmarks(userId: Long, articleId: Long) = {
    DB.withConnection { implicit c =>
      SQL(
        """
        insert into bookmarks (user_id,article_id,bookmark_date) values
        ({user_id},{article_id},{bookmark_date})
        """
      ).on(
        'user_id -> userId,
        'article_id -> articleId,
        'bookmark_date -> new Timestamp(DateTime.now().getMillis())
      ).executeInsert()
    }
    "bookmark.success"
  }

  def unbookmarks(userId: Long, articleId: Long) = {

    DB.withConnection { implicit c =>
      SQL(
        """
        DELETE FROM bookmarks
        WHERE bookmarks.user_id = {userId} AND bookmarks.author_id = {articleId}
        """).
        on(
          "userId" -> userId,
          "articleId" -> articleId
        ).executeUpdate()
    }
    "unbookmark.success"
  }

  def hasBookmarked(userId: Long, articleId: Long): Boolean = {

    DB.withConnection { implicit c =>
      SQL(
        """
      SELECT bookmarks.id from bookmarks
      WHERE bookmarks.user_id = {userId} and bookmarks.article_id = {articleId}
        """
      ).
        on("userId" -> userId,
          "articleId" -> articleId
        )
        .as(recordMapperId.singleOpt) match {
        case None => false
        case Some(id) => true
      }
    }
  }

  def bookmarksOrUnbookmarks(followerId: Long, articleId: Long): String = {
    if (ArticleRepository.isDraft(articleId)) "error.isDraft"
    else {
      if (hasBookmarked(followerId, articleId)) unbookmarks(followerId, articleId)
      else bookmarks(followerId, articleId)
    }
  }
}