package models

import java.util.Date

import org.joda.time.DateTime
import play.api.libs.json.{Json, JsObject, Writes}

/**
  * Created by corpus on 22/02/2016.
  */
case class MyProfile(

                      authorFollowersId: Long
                    )


case class AuthorFollowers(
                            authorId: Long,
                            followDate: Date,
                            nbFollowers: Int,
                            commulativeNbFollowers: Int,
                            maxNbFollowers: Int

                          )

case class AuthorViews(authorId: Long,
                       viewDate: Date,
                       nbViews: Int,
                       commulativeNbViews: Int,
                       maxNbViews: Int)

case class AuthorLikes(
                        authorId: Long,
                        likeDate: Date,
                        nbLikes: Int,
                        commulativeNbLikes: Int,
                        maxNbLikes: Int
                      )

object AuthorFollowers {

  implicit val authorFollowersWriter = new Writes[AuthorFollowers] {
    def writes(authorFollowers: AuthorFollowers): JsObject = {
       Json.obj(
        "authorId" -> authorFollowers.authorId,
        "followDate" -> authorFollowers.followDate,
        "nbFollowers" -> authorFollowers.nbFollowers,
        "commulativeNbFollowers" -> authorFollowers.commulativeNbFollowers,
        "maxNbFollowers" -> authorFollowers.maxNbFollowers
      )
    }
  }
}