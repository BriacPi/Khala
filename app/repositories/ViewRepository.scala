package repositories


import java.sql.Timestamp

import anorm.SqlParser._
import anorm._
import anorm.Sql._
import play.api.db.DB

import org.joda.time.DateTime
import play.api.Play.current

/**
  * Created by corpus on 20/02/2016.
  */
//case class ViewRepository(
//                           id: Option[Long],
//                           userId: Long,
//                           articleId: Long,
//                           viewDate: DateTime
//                         )


trait ViewRepository {
//  private[repositories] val recordMapperArticleViews = {
//    long("articles_views.article_id") ~
//      int("articles_views.nb_views") map {
//      case id ~ nbViews => {
//        nbViews
//      }
//    }
}

object ViewRepository extends ViewRepository {

  def create(userId: Long, articleId: Long) = {
    DB.withConnection { implicit c =>
      SQL(
        """
        insert into views (user_id,article_id,view_date) values
        ({user_id},{article_id},{view_date})
        """
      ).on(
        'user_id -> userId,
        'article_id -> articleId,
        'view_date -> new Timestamp(DateTime.now().getMillis())
      ).executeInsert()
    }
  }

}