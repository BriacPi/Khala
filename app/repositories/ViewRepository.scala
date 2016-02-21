package repositories


import java.sql.Timestamp

import anorm.SqlParser._
import anorm._
import models.ArticleNbs
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

  def create(userId: Long, articleId: Long): String = {

    ArticleRepository.getById(articleId) match {
      case Some(article) => {
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
        ArticleRepository.updateArticleStats(articleId,ArticleNbs(articleId,1,0,0,0))
        "view.add.success"
      }
      case None => "article.notFound"
    }
  }

}