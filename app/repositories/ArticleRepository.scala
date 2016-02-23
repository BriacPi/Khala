package repositories

/**
  * Created by corpus on 19/02/2016.
  */


import scala.util.{Failure, Success}
import models._
import org.joda.time.DateTime


import anorm.SqlParser._
import anorm._
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
      long("author_id") ~
      get[DateTime]("articles.creation_date") ~
      get[DateTime]("articles.last_update") ~
      str("articles.title") ~
      str("articles.summary") ~
      str("articles.content") ~
      int("articles.nb_modifications") ~
      int("articles.reading_time") ~
      str("articles.tag1") ~
      str("articles.tag2") ~
      str("articles.status") map {
      case id ~ author_id ~ creationDate ~ lastUpdate ~ title ~ summary ~ content ~ nbModifications ~
        readingTime ~ tag1 ~ tag2 ~ status => {
        Article(Some(id), author_id, creationDate, lastUpdate,
          title, Some(summary), content, nbModifications, readingTime, tag1, Some(tag2), status)
      }
    }
  }
  private[repositories] val recordMapperArticleNbs = {

    long("articles_stats.article_id") ~
      int("articles_stats.nb_views") ~
      int("articles_stats.nb_likes") ~
      int("articles_stats.nb_comments") ~
      int("articles_stats.nb_bookmarks") map {
      case id ~ nbViews ~ nbLikes ~ nbComments ~ nbBookmarks => {
        ArticleNbs(id, nbViews, nbLikes, nbComments, nbBookmarks)
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

  def create(article: Article): String = {
    getByAuthorAndTitle(article.authorId, article.title) match {
      case None => {
        DB.withConnection { implicit c =>
          SQL(
            """
              insert into articles (author_id,creation_date,last_update,title,summary,content,
              nb_modifications,reading_time, tag1, tag2, status) values
              ({author_id},{creation_date},{last_update},{title},{summary},{content},
              {nb_modifications},{reading_time}, {tag1},{tag2},{status})
            """
          ).on(
            'author_id -> article.authorId,
            'creation_date -> new Timestamp(article.creationDate.getMillis()),
            'last_update -> new Timestamp(article.lastUpdate.getMillis()),
            'title -> article.title,
            'summary -> article.summary.getOrElse(""),
            'content -> article.content,
            'nb_modifications -> article.nbModifications,
            'reading_time -> math.max(article.content.length / 1150, 1),
            'tag1 -> article.tag1,
            'tag2 -> article.tag2.getOrElse[String](""),
            'status -> article.status
          ).executeInsert()
        }
        if (article.status == "draft") "draft.add.success"
        else {
          TaggingRepository.create(article.tag1, article.id.get)
          article.tag2 match {
            case None =>
            case Some(tag2) => TaggingRepository.create(tag2, article.id.get)
          }
          "article.add.success"
        }
      }
      case Some(existingArticle) => "article.add.alreadyExists"
    }

  }

  //status is public or private
  def draftToArticle(draftId: Long, status: String) = {
    val optDraft: Option[Article] = getById(draftId)
    optDraft match {
      case None =>
      case Some(draft) => {
        TaggingRepository.create(draft.tag1, draft.id.get)
        draft.tag2 match {
          case None =>
          case Some(tag2) => TaggingRepository.create(tag2, draft.id.get)
        }
        val newArticle = draft.copy(status = status, creationDate = DateTime.now())
        update(newArticle)
      }
    }

  }

  def update(article: Article): String = {
    val newArticle = article.copy(lastUpdate = DateTime.now(), nbModifications = article.nbModifications + 1,
      readingTime = math.max(article.content.length / 1150, 1))
    val oldArticle= getById(article.id.get).get
    DB.withConnection { implicit c =>
      SQL(
        """
        update  articles set creation_date = {creation_date}, last_update ={last_update},title={title},summary={summary}
        ,content={content},nbModifications={nbModifications},readingTime={readingTime}, ta1 = {tag1}, tag2 = {tag2}
        WHERE id ={id}
        """
      ).on(
        'id -> newArticle.id.get,
        'creation_date -> new Timestamp(newArticle.creationDate.getMillis()),
        'last_update -> new Timestamp(newArticle.lastUpdate.getMillis()),
        'title -> newArticle.title,
        'summary -> newArticle.summary.getOrElse(""),
        'content -> newArticle.content,
        'nbModifications -> newArticle.nbModifications,
        'readingTime -> newArticle.readingTime,
        'tag1 -> newArticle.tag1,
        'tag2 -> newArticle.tag2.getOrElse[String]("")
      ).executeUpdate()
    }
    if (newArticle.status != "draft") {
      TaggingRepository.removeFromArticle(newArticle.id.get)
      TaggingRepository.create(newArticle.tag1, newArticle.id.get)
      newArticle.tag2 match {
        case None =>
        case Some(tag2) => TaggingRepository.create(tag2, newArticle.id.get)
      }
      "article.update.success"
    }
    else "draft.update.success"
  }

  def save(article: Article): String = {
    article.id match {
      case None => create(article)
      case Some(id) => update(article)
    }
  }


  def getArticleNbs(articleId: Long): Option[ArticleNbs] = {
    DB.withConnection { implicit current =>
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
    val datetime: Timestamp = new Timestamp(DateTime.now().minusDays(5).getMillis())

    DB.withConnection { implicit current =>
      SQL(
        """
          SELECT *
          FROM articles
          WHERE articles.creation_date > {nowMinus5Day} AND articles.status != 'draft'
          LIMIT 200
        """
      )
        .on("nowMinus5Day" -> datetime)
        .as(recordMapperArticle *)
        .toList
    }

  }

  def getDraftsByAuthor(authorId: Long): List[Article] = {
    DB.withConnection { implicit current =>
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
    DB.withConnection { implicit current =>
      SQL(
        """
          SELECT articles.status FROM articles
          WHERE articles.id = {articleId} AND  articles.status ='draft'
        """
      )
        .on("articleId" -> articleId)
        .as(recordMapperArticle.singleOpt)
    } match {
      case None => false
      case Some(article) => true

    }
  }

  //return articles
  def getArticleStatsByAuthor(authorId: Long): List[ArticleStats] = {
    val listArticle: List[Article] = DB.withConnection { implicit current =>
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

    listArticle.map { article =>
      ArticleStats.fromArticle(Article.shorten(article), getArticleNbs(article.id.get).get)
    }

  }

  def getTopArticleStatsByViews() = {
    val datetime: Timestamp = new Timestamp(DateTime.now().minusDays(5).getMillis())
    val listArticle: List[Article] = DB.withConnection { implicit current =>
      SQL(
        """
            SELECT articles.* FROM articles
            LEFT JOIN articles_views ON articles.id = articles_views.article_id
            WHERE articles.creation_date > {nowMinus5Day} AND articles.status!='draft'
            ORDER BY articles_views.nb_views DESC, articles.id DESC
            LIMIT 200 OFFSET 0
        """
      )
        .on("nowMinus5Day" -> datetime)
        .as(recordMapperArticle *)
        .toList
    }
    listArticle.map { article =>
      ArticleStats.fromArticle(Article.shorten(article), getArticleNbs(article.id.get).get)
    }
  }

  def getArticleStats(articleId: Long): Option[ArticleStats] = {
    val optArticle: Option[Article] = DB.withConnection { implicit current =>
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

//  def initializeArticleStats(articleId: Long) = {
//    DB.withConnection { implicit c =>
//      SQL(
//        """
//        INSERT into articles_stats (article_id) values
//        ({id})
//        """
//      ).on(
//        "id" -> articleId
//      ).executeInsert()
//    }
//  }
//
//  def deleteArticleStats(articleId: Long) = {
//    DB.withConnection { implicit c =>
//      SQL(
//        """
//        DELETE FROM articles_stats
//        WHERE articles_stats.article_id= {id}
//        """).
//        on(
//          "id" -> articleId
//        ).executeUpdate()
//    }
//  }
//
//  def updateArticleStats(articleId: Long, modifier: ArticleNbs): Unit = {
//
//    DB.withConnection { implicit c =>
//      SQL(
//        """
//        update articles_stats
//        SET nb_views = nb_views+{modifier_views}, nb_likes =nb_likes+{modifier_likes},
//        nb_comments =nb_comments+{modifier_comments}, nb_bookmarks=nb_bookmarks+{modifier_bookmarks}
//        WHERE article_id = {articleId}
//        """
//      ).on(
//        "modifier_views" -> modifier.nbViews,
//        "modifier_likes" -> modifier.nbLikes,
//        "modifier_comments" -> modifier.nbComments,
//        "modifier_bookmarks" -> modifier.nbBookmarks,
//        "articleId" -> articleId
//      ).executeUpdate()
//    }
//  }
}