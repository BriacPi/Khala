package repositories.Contents

import anorm.SqlParser._
import anorm._
import play.api.Play.current
import play.api.db.DB

/**
  * Created by corpus on 20/02/2016.
  */
trait TagRepository {
  private[repositories] val recordMapperName = {
    str("tags.name") map {
      case name => {
        name
      }
    }
  }
}

object TagRepository extends TagRepository {

  def exists(tagName: String): Boolean = {
    val lowerCaseTagName = tagName.toLowerCase()
    DB.withConnection { implicit c =>
      SQL(
        """
      SELECT * from tags
      WHERE tags.name = {name}
        """
      ).
        on(
          "name" -> lowerCaseTagName
        )
        .as(recordMapperName.singleOpt) match {
        case None => false
        case Some(name) => true
      }


    }
  }

  def create(tagName: String): String = {
    if (exists(tagName)) "tag.add.alreadyExists"
    else {
      print("c'est pas normal")
      val lowerCaseTagName: String = tagName.toLowerCase()
      DB.withConnection { implicit c =>
        SQL(
          """
        INSERT into tags (name) values
        ({name})
          """
        ).on(
          'name -> lowerCaseTagName
        ).executeInsert()
      }
      "tag.add.success"
    }
  }

  def remove(tagName: String): String = {
    val lowerCaseTagName = tagName.toLowerCase()
    DB.withConnection { implicit c =>
      SQL(
        """
        DELETE FROM tags
        WHERE tags.name = {name}
        """).
        on(
          "name" -> lowerCaseTagName
        ).executeUpdate()
      "tag.remove.success"
    }
  }
}