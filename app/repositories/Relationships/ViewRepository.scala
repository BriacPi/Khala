package repositories.Relationships

import java.sql.Timestamp

import anorm._
import org.joda.time.DateTime
import play.api.Play.current
import play.api.db.DB
import repositories.Contents.ArticleRepository

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
    ArticleRepository.getNoDraftById(articleId) match {
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
        "view.add.success"
      }
      case None => "article.notFound"
    }
  }

}