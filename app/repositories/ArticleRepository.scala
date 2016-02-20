package repositories

/**
  * Created by corpus on 19/02/2016.
  */


import scala.util.{Failure, Success}
import models.{ArticleStats, Article, User}
import org.joda.time.DateTime


import anorm.SqlParser._
import anorm._
import anorm.Sql._
import play.api.db.DB
import scala.language.postfixOps
import play.api.Play.current
import java.sql.Timestamp

/**
  * Created by corpus on 05/02/2016.
  */
trait ArticleRepository {
  private[repositories] val recordMapperArticle = {
    long("articles.id") ~
      get[DateTime]("articles.creation_date") ~
      get[DateTime]("articles.last_update") ~
      str("articles.title") ~
      str("articles.summary") ~
      str("articles.content") ~
      int("articles.nb_modifications") ~
      int("articles.reading_time") ~
      str("articles.tag1") ~
      str("articles.tag2") map {
      case id ~ creationDate ~ lastUpdate ~ title ~ summary ~ content ~ nbModifications ~
        readingTime ~ tag1 ~ tag2 => {
        Article(Some(id), creationDate, lastUpdate,
          title, Some(summary), content, nbModifications, readingTime, tag1, Some(tag2))
      }
    }
  }
  private[repositories] val recordMapperArticleViews = {
    long("articles_views.article_id") ~
      int("articles_views.nb_views") map {
      case id ~ nbViews => {
        nbViews
      }
    }
  }
  private[repositories] val recordMapperArticleLikes = {
    long("articles_likes.article_id") ~
      int("articles_likes.nb_likes") map {
      case id ~ nbViews => {
        nbViews
      }
    }
  }

  private[repositories] val recordMapperArticleComments = {
    long("articles_comments.article_id") ~
      int("articles_comments.nb_views") map {
      case id ~ nbViews => {
        nbViews
      }
    }
  }
}


object ArticleRepository extends ArticleRepository {

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

  def getByAuthorAndTitle(authorId: Long, title: String): Option[Article] = {
    DB.withConnection { implicit current =>
      SQL(
        """
          SELECT *
          FROM articles
          WHERE articles.author_id = {authorId} AND articles.title = {title}
        """
      )
        .on(
          "authorId" -> authorId,
          "title" -> title
        )
        .as(recordMapperArticle.singleOpt)
    }

  }

  def create(authorId: Long, article: Article): String = {
    getByAuthorAndTitle(authorId, article.title) match {
      case None => {
        DB.withConnection { implicit c =>
          SQL(
            """
        insert into articles (author_id,creation_date,last_update,title,summary,content,
        nb_modifications,reading_time, tag1, tag2) values
        ({author_id},{creation_date},{last_update},{title},{summary},{content},
        {nb_modifications},{reading_time}, {tag1},{tag2})
            """
          ).on(
            'author_id -> authorId,
            'creation_date -> new Timestamp(article.creationDate.getMillis()),
            'last_update -> new Timestamp(article.lastUpdate.getMillis()),
            'title -> article.title,
            'summary -> article.summary.getOrElse(""),
            'content -> article.content,
            'nb_modifications -> article.nbModifications,
            'reading_time -> article.content.length / 1150,
            'tag1 -> article.tag1,
            'tag2 -> article.tag2.getOrElse[String]("")
          ).executeInsert()
        }
        val newArticle: Option[Article] = getByAuthorAndTitle(authorId, article.title)
        TaggingRepository.create(article.tag1, newArticle.get.id.get)
        article.tag2 match {
          case None =>
          case Some(tag2) => TaggingRepository.create(tag2, newArticle.get.id.get)
        }
        "article.add.success"
      }
      case Some(existingArticle) => "article.add.alreadyExists"
      }

    }



  def update(authorId: Long, article: Article): String = {

    DB.withConnection { implicit c =>
      SQL(
        """
        update  articles set last_update ={last_update},title={title},summary={summary},content={content},
        nbModifications={nbModifications},readingTime={readingTime} where id ={id}
        """
      ).on(
        'id -> article.id.get,
        'last_update -> new Timestamp(DateTime.now().getMillis()),
        'title -> article.title,
        'summary -> article.summary.getOrElse(""),
        'content -> article.content,
        'nbModifications -> (article.nbModifications + 1),
        'readingTime -> article.content.length / 1150
      ).executeUpdate()
    }
    val newArticle: Option[Article] = getByAuthorAndTitle(authorId, article.title)
    TaggingRepository.removeFromArticle(article.id.get)
    TaggingRepository.create(article.tag1, newArticle.get.id.get)
    article.tag2 match {
      case None =>
      case Some(tag2) => TaggingRepository.create(tag2, newArticle.get.id.get)
    }
    "article.update.success"
  }

  def save(author: User, article: Article): String = {
    article.id match {
      case None => create(author.id.get, article)
      case Some(id) => update(author.id.get,article)
    }
  }


  def getViews(articleId: Long) = {
    DB.withConnection { implicit current =>
      SQL(
        """
          SELECT *
          FROM articles_views
          WHERE articles_views.article_id = {articleId}
        """
      )
        .on("articleId" -> articleId)
        .as(recordMapperArticleViews.singleOpt)
    }.getOrElse(0)
  }

  def getLikes(articleId: Long) = {
    DB.withConnection { implicit current =>
      SQL(
        """
          SELECT *
          FROM articles_likes
          WHERE articles_likes.article_id = {articleId}
        """
      )
        .on("articleId" -> articleId)
        .as(recordMapperArticleLikes.singleOpt)
    }.getOrElse(0)
  }

  def getComments(articleId: Long) = {
    DB.withConnection { implicit current =>
      SQL(
        """
          SELECT *
          FROM articles_comments
          WHERE articles_comments.article_id = {articleId}
        """
      )
        .on("articleId" -> articleId)
        .as(recordMapperArticleComments.singleOpt)
    }.getOrElse(0)
  }

  def getAllArticles(): List[Article] = {
    val datetime: Timestamp = new Timestamp(DateTime.now().minusHours(24).getMillis())

    DB.withConnection { implicit current =>
      SQL(
        """
          SELECT * FROM articles
          WHERE articles.creation_date > {nowMinus1Day}
        """
      )
        .on("nowMinus1Day" -> datetime)
        .as(recordMapperArticle *)
        .toList
    }

  }


  //return articles
  def getArticleStatsByAuthor(authorId: Long): List[ArticleStats] = {
    val listArticle: List[Article] = DB.withConnection { implicit current =>
      SQL(
        """
            SELECT articles.* FROM articles
            LEFT JOIN articles_views ON articles.id = articles_views.article_id
            WHERE articles.author_id = {authorId}
            ORDER BY articles_views.nb_views DESC, articles.id DESC
        """
      )
        .on("authorId" -> authorId)
        .as(recordMapperArticle *)
        .toList
    }

    listArticle.map { article =>

      ArticleStats.fromArticle(Article.shorten(article), getViews(article.id.get),
        getLikes(article.id.get), getComments(article.id.get))
    }

  }

  def getTopArticleStatsByViews() = {
    val datetime: Timestamp = new Timestamp(DateTime.now().minusHours(24).getMillis())
    val listArticle: List[Article] = DB.withConnection { implicit current =>
      SQL(
        """
            SELECT articles.* FROM articles
            LEFT JOIN articles_views ON articles.id = articles_views.article_id
            WHERE articles.creation_date > {nowMinus1Day}
            ORDER BY articles_views.nb_views DESC, articles.id DESC
        """
      )
        .on("nowMinus1Day" -> datetime)
        .as(recordMapperArticle *)
        .toList
    }
    listArticle.map { article =>
      ArticleStats.fromArticle(Article.shorten(article), getViews(article.id.get),
        getLikes(article.id.get), getComments(article.id.get))
    }


  }

  def getArticleStat(articleId: Long): Option[ArticleStats] = {

    val optArticle: Option[Article] = DB.withConnection { implicit current =>
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
    optArticle match {
      case None => None
      case Some(article) => Some(ArticleStats.fromArticle(article, getViews(article.id.get),
        getLikes(article.id.get), getComments(article.id.get)))
    }
  }
}