package models.userProfile

import play.api.libs.json._
import play.api.libs.functional.syntax._
/**
  * Created by corpus on 14/02/2016.
  */
case class influenceInfo (
                           totalNbLikes: Int,
                           nbFollowers: Int,
                           nbFollowings: Int,
                           nbArticles: Int

                         )



object InfluenceInfo {

}