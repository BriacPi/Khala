package repositories.Contents

/**
  * Created by corpus on 19/02/2016.
  */


import java.sql.Timestamp

import anorm.SqlParser._
import anorm._
import models._
import org.joda.time.DateTime
import play.api.Play.current
import play.api.db.DB
import repositories.Relationships.TaggingRepository

import scala.language.postfixOps

/**
  * Created by corpus on 05/02/2016.
  */
trait ArticleRepository {
  private[repositories] val recordMapperArticle = {
    long("articles.id") ~
      long("author_id") ~
      get[DateTime]("articles.creation_date") ~
      get[DateTime]("articles.last_update") ~
      str("articles.title") ~
      str("articles.summary") ~
      str("articles.content") ~
      int("articles.nb_modifications") ~
      int("articles.reading_time") ~
      str("articles.status") map {
      case id ~ author_id ~ creationDate ~ lastUpdate ~ title ~ summary ~ content ~ nbModifications ~
        readingTime ~ status => {
        Article(Some(id), author_id, creationDate, lastUpdate,
          title, Some(summary), content, nbModifications, readingTime, status)
      }
    }
  }
  private[repositories] val recordMapperArticleNbs = {

    long("articles_stats.article_id") ~
      int("articles_stats.nb_views") ~
      int("articles_stats.nb_likes") ~
      int("articles_stats.nb_reactions") ~
      int("articles_stats.nb_bookmarks") map {
      case id ~ nbViews ~ nbLikes ~ nbReactions ~ nbBookmarks => {
        ArticleNbs(id, nbViews, nbLikes, nbReactions, nbBookmarks)
      }
    }
  }

  private[repositories] val recordMapperEditable = {

    long("articles.id") ~
      str("articles.title") ~
      str("article.summary") ~
      str("articles.content") map {
      case id ~ title ~ summary ~ content => {
        ArticleUserEditable(Some(id), title, Some(summary), content)
      }
    }
  }

  private[repositories] val recordMapperIsDraft = {
    str("articles.status") map {
      case status => {
        status
      }
    }
  }

  private[repositories] val recordMapperId = {
    long("articles.id") map {
      case id => {
        id
      }
    }
  }
}


object ArticleRepository extends ArticleRepository {


  //return a no draft
  def getById(articleId: Long): Option[Article] = {
    DB.withConnection { implicit current =>
      SQL(
        """
          SELECT *
          FROM articles
          WHERE articles.id = {id}
        """
      )
        .on("id" -> articleId)
        .as(recordMapperArticle.singleOpt)
    }
  }

  def getNoDraftById(articleId: Long): Option[Article] = {
    DB.withConnection { implicit current =>
      SQL(
        """
          SELECT *
          FROM articles
          WHERE articles.id = {id} AND status !='draft'
        """
      )
        .on("id" -> articleId)
        .as(recordMapperArticle.singleOpt)
    }
  }

  def getIdByAuthorAndDate(authorId: Long, creationDate: DateTime): Option[Long] = {
    DB.withConnection { implicit current =>
      SQL(
        """
          SELECT articles.id
          FROM articles
          WHERE articles.author_id = {authorId} AND articles.creation_date = {creationDate}
        """
      )
        .on(
          "authorId" -> authorId,
          "creationDate" -> new Timestamp(creationDate.getMillis())
        )
        .as(recordMapperId.singleOpt)
    }


  }

  def create(article: Article): String = {
    article.id match {
      case None => {

        DB.withConnection { implicit c =>
          SQL(
            """
              insert into articles (author_id,creation_date,last_update,title,summary,content,
              nb_modifications,reading_time, status) values
              ({author_id},{creation_date},{last_update},{title},{summary},{content},
              {nb_modifications},{reading_time},{status})
            """
          ).on(
            'author_id -> article.authorId,
            'creation_date -> new Timestamp(article.creationDate.getMillis()),
            'last_update -> new Timestamp(article.lastUpdate.getMillis()),
            'title -> article.title,
            'summary -> article.summary.getOrElse(""),
            'content -> article.content,
            'nb_modifications -> article.nbModifications,
            'reading_time -> Article.getReadingTime(article.content),
            'status -> "draft"
          ).executeInsert()
        }
        "draft.add.success"
      }
      case Some(existingDraft) => "draft.add.error.alreadyExists"
    }

  }

  //status is public or private
  //publish does not update but publish can switch the status to and from public/private.
  def publish(authorId: Long, article: Article) = {

    if (authorId != article.authorId) "Damn you wann publish other people's draft?"
    else {

      val sameNameArticle = DB.withConnection { implicit current =>
        SQL(
          """
          SELECT articles.id
          FROM articles
          WHERE articles.title = {title} AND articles.status = {status}
          """
        )
          .on(
            "title" -> article.title,
            "status" -> article.status
          )
          .as(recordMapperId.singleOpt)
      }
      sameNameArticle match {
        case Some(s) =>
        case None =>
          val optArticle: Option[Article] = getById(article.id.get)
          optArticle match {
            case None => "Sorry you already published an article with same name."
            case Some(oldArticle) => {
              //The logic is save has been applied just before, so if any tagging has to be done
              //it would have been done beforehand except when it is a draft.
              if (oldArticle.status == "draft") {
                TaggingRepository.removeFromArticle(article.id.get)
                //            TaggingRepository.create(article.tag1, authorId,article.id.get)
                //            article.tag2 match {
                //              case None =>
                //              case Some(tag2) => TaggingRepository.create(tag2,authorId, article.id.get)
                //            }
              }
              //Changing status
              DB.withConnection {
                implicit c =>
                  SQL(
                    """
  update articles set status = {status}
  WHERE id ={id}
                    """
                  ).on(
                    'id -> article.id.get,
                    'status -> article.status
                  ).executeUpdate()
              }
            }
          }
          "publish.success"
      }
    }
  }

  //does not change the nature of the article/draft
  def update(authorId: Long, article: Article): String = {
    if (authorId != article.authorId) "Updating another gal's work, not cool."
    else {
      val oldArticle = getById(article.id.get).get
      val newArticle = article.copy(lastUpdate = DateTime.now(), nbModifications = oldArticle.nbModifications + 1,
        readingTime = Article.getReadingTime(article.content), status = oldArticle.status)

      DB.withConnection {
        implicit c =>
          SQL(
            """
update  articles set creation_date = {creation_date}, last_update ={last_update},title={title},summary={summary}
,content={content},nb_modifications={nbModifications},reading_time={readingTime}
WHERE id ={id}
            """
          ).on(
            'id -> newArticle.id.get,
            'creation_date -> new Timestamp(oldArticle.creationDate.getMillis()),
            'last_update -> new Timestamp(newArticle.lastUpdate.getMillis()),
            'title -> newArticle.title,
            'summary -> newArticle.summary.getOrElse(""),
            'content -> newArticle.content,
            'nbModifications -> newArticle.nbModifications,
            'readingTime -> newArticle.readingTime
          ).executeUpdate()
      }
      if (oldArticle.status != "draft") {
        TaggingRepository.removeFromArticle(newArticle.id.get)
        //        TaggingRepository.create(newArticle.tag1, authorId,newArticle.id.get)
        //        newArticle.tag2 match {
        //          case None =>
        //          case Some(tag2) => TaggingRepository.create(tag2,authorId, newArticle.id.get)
        //        }
        "article.update.success"
      }
      else "draft.update.success"
    }

  }


  def save(authorId: Long, article: Article): String = {
    article.id match {
      case None => create(article)
      case Some(id) => update(authorId, article)
    }
  }

  def delete(authorId: Long, article: Article): String = {
    if (article.authorId != authorId) "Oh, please have mercy with other people's work."
    else {
      DB.withConnection {
        implicit c =>
          SQL(
            """
DELETE FROM articles
WHERE articles.id = {articleId}
            """
          ).on(
            "articleId" -> article.id.get
          ).executeUpdate()
      }
    }
    "We're so sad you deleted this jewel, but it's only to come back stronger, we know it!"

  }

  def getEditable(authorId: Long, articleId: Long) = {
    DB.withConnection {
      implicit current =>
        SQL(
          """
SELECT articles.id, articles.title, articles.summary, articles.content
FROM articles
WHERE articles.id = {articleId} AND articles.author_id = {authorId}
          """
        )
          .on(
            "authorId" -> authorId,
            "articleId" -> articleId
          )
          .as(recordMapperEditable.singleOpt)
    }
  }

  //it's automatically not a draft.
  def getArticleNbs(articleId: Long): Option[ArticleNbs] = {
    DB.withConnection {
      implicit current =>
        SQL(
          """
SELECT *
FROM articles_stats
WHERE articles_stats.article_id={id}
          """
        )
          .on(
            "id" -> articleId
          )
          .as(recordMapperArticleNbs.singleOpt)
    }
  }

  def getLikes(articleId: Long) = {
    getArticleNbs(articleId) match {
      case None => 0
      case Some(articleNbs) => articleNbs.nbLikes
    }
  }

  def getAllArticles(): List[Article] = {
    val datetime: Timestamp = new Timestamp(DateTime.now().minusDays(7).getMillis())

    DB.withConnection {
      implicit current =>
        SQL(
          """
SELECT *
FROM articles
WHERE articles.creation_date > {nowMinus7Day} AND articles.status = 'public'
LIMIT 200
          """
        )
          .on("nowMinus7Day" -> datetime)
          .as(recordMapperArticle *)
          .toList
    }

  }

  def getDraftsByAuthor(authorId: Long): List[Article] = {
    DB.withConnection {
      implicit current =>
        SQL(
          """
SELECT * FROM articles
WHERE articles.author_id = {authorId} AND articles.status ='draft'
          """
        )
          .on("authorId" -> authorId)
          .as(recordMapperArticle *)
          .toList
    }
  }

  def isDraft(articleId: Long): Boolean = {
    DB.withConnection {
      implicit current =>
        SQL(
          """
SELECT articles.status FROM articles
WHERE articles.id = {articleId} AND  articles.status ='draft'
          """
        )
          .on("articleId" -> articleId)
          .as(recordMapperIsDraft.singleOpt)
    } match {
      case None => false
      case Some(article) => true

    }
  }

  //return articles
  def getArticleStatsByAuthor(authorId: Long): List[ArticleStats] = {
    val listArticle: List[Article] = DB.withConnection {
      implicit current =>
        SQL(
          """
SELECT articles.* FROM articles
LEFT JOIN articles_views ON articles.id = articles_views.article_id
WHERE articles.author_id = {authorId} AND articles.status!='draft'
ORDER BY COALESCE(articles_views.nb_views,0) DESC, articles.id DESC
          """
        )
          .on("authorId" -> authorId)
          .as(recordMapperArticle *)
          .toList
    }

    listArticle.map {
      article =>
        ArticleStats.fromArticle(Article.shorten(article), getArticleNbs(article.id.get).get)
    }

  }

  def getTopArticleStatsByViews() = {
    val datetime: Timestamp = new Timestamp(DateTime.now().minusDays(7).getMillis())
    val listArticle: List[Article] = DB.withConnection {
      implicit current =>
        SQL(
          """
SELECT articles.* FROM articles
LEFT JOIN articles_views ON articles.id = articles_views.article_id
WHERE articles.creation_date > {nowMinus7Day} AND articles.status!='draft'
ORDER BY articles_views.nb_views DESC, articles.id DESC
LIMIT 200 OFFSET 0
          """
        )
          .on("nowMinus7Day" -> datetime)
          .as(recordMapperArticle *)
          .toList
    }
    listArticle.map {
      article =>
        ArticleStats.fromArticle(Article.shorten(article), getArticleNbs(article.id.get).get)
    }
  }

  def getArticleStats(articleId: Long): Option[ArticleStats] = {
    val optArticle: Option[Article] = DB.withConnection {
      implicit current =>
        SQL(
          """
SELECT *
FROM articles
WHERE articles.id = {id} AND articles.status!='draft'
          """
        )
          .on("id" -> articleId)
          .as(recordMapperArticle.singleOpt)
    }
    optArticle match {
      case None => None
      case Some(article) => Some(ArticleStats.fromArticle(Article.shorten(article), getArticleNbs(article.id.get).get))
    }
  }

  def getRecentUntitledDraft(authorId: Long): Option[Article] = {
    val oneHourEarlier = new Timestamp(DateTime.now().minusHours(1).getMillis())
    DB.withConnection {
      implicit current =>
        SQL(
          """
SELECT *
FROM articles
WHERE author_id = {authorId} AND status='draft' AND title = '' AND content = '' AND
creation_date>{oneHourEarlier}
          """
        )
          .on(
            "authorId" -> authorId,
            "oneHourEarlier" -> oneHourEarlier
          )
          .as(recordMapperArticle.singleOpt)
    }
  }

}