package repositories.Publications

import java.sql.Timestamp

import anorm.SqlParser._
import anorm._
import models.{Publication, PublicationMember}
import org.joda.time.DateTime
import play.api.db.DB
import play.api.Play.current

/**
  * Created by corpus on 25/02/2016.
  */
trait PublicationRepository {
  private[repositories] val recordMapperPublication = {
    long("publications.id") ~
      str("publications.name") ~
      str("publications.descrption") ~
      long("publications.creator_id") ~
      get[DateTime]("publications.creation_date") map {
      case id ~ name ~ description ~ creatorId ~ creationDate => {
        Publication(Some(id), name,description, creatorId, creationDate)
      }
    }
  }
  private[repositories] val recordMapperPublicationMember = {
    str("publication_members.publication_name") ~
      long("publication_members.member_id") ~
      get[DateTime]("publication_members.join_date") ~
      str("publication_members.title") map {
      case name ~ member_id ~ join_date ~ title => {
        PublicationMember(name, member_id, join_date, title)
      }
    }
  }
  private[repositories] val recordMapperId = {
    long("publications.id") map {
      case id => {
        id
      }
    }
  }
}

object PublicationRepository extends PublicationRepository {
  def getByUserAndDate(creatorId: Long, creationDate: DateTime): Option[Long] = {
    DB.withConnection { implicit c =>
      SQL(
        """
          SELECT id FROM publications
          WHERE user_id = {creatorId} AND creation_date = {creationDate}
        """
      )
        .on(
          "creatorId" -> creatorId,
          "creationDate" -> new Timestamp(creationDate.getMillis())
        ).as(recordMapperId.singleOpt)

    }
  }

  def create(publication: Publication) = {
    if (!publication.id.isEmpty) "Authorization: denied."
    else {

      DB.withConnection { implicit c =>
        SQL(
          """
              INSERT into publications(name,description,creatorId,parent_type,creation_date,content)
              VALUES({name},{description},{userId},{creationDate})
          """
        ).on(

          "name" -> publication.name,
          "description" -> publication.description,
          "creatorId" -> publication.creatorId,
          "creationDate" -> new Timestamp(publication.creationDate.getMillis())
        ).executeInsert()
      }
    }
    //blame it on the boogie.
  }

  def isMember(userId: Long, publication: Publication) = {
    val listAdministrators = DB.withConnection { implicit c =>
      SQL(
        """
              DELETE FROM publication_members
              WHERE publication_name = {publicationName} AND member_id = {userId}
        """
      ).on(
        "publicationName" -> publication.name
      ).as(recordMapperPublicationMember.singleOpt)
    }

    match {
      case None => false
      case Some(admin) => true
    }
  }

  def isAdministrator(userId: Long, publication: Publication): Boolean = {
    DB.withConnection { implicit c =>
      SQL(
        """
              DELETE FROM publication_members
              WHERE publication_name = {publicationName} AND title = 'administrator' AND member_id ={userId}
        """
      ).on(
        "userId" -> userId,
        "publicationName" -> publication.name
      ).as(recordMapperPublicationMember.singleOpt)

    } match {
      case None => false
      case Some(admin) => true
    }
  }


  def delete(userId: Long, publication: Publication) = {

    val isAdmin: Boolean = isAdministrator(userId, publication)
    if (!isAdmin) "Ask the right before exterminating the publication, please."
    else {
      DB.withConnection { implicit c =>
        SQL(
          """
              DELETE FROM publications
              WHERE id = {publicationId}
          """
        ).on(
          "publicationId" -> publication.id.get
        ).executeUpdate()
      }
    }
    "We're so sad you deleted this jewel, but it's only to come back stronger, we know it!"
  }

}
