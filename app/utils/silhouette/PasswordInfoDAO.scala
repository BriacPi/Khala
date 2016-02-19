package utils.silhouette

import models.User
import com.mohiva.play.silhouette.api.util.PasswordInfo
import com.mohiva.play.silhouette.impl.daos.DelegableAuthInfoDAO
import com.mohiva.play.silhouette.api.LoginInfo
import reactivemongo.bson.{BSONString, BSONDocument}
import repositories.UserRepository
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import Implicits._

class PasswordInfoDAO extends DelegableAuthInfoDAO[PasswordInfo] {

  def add(loginInfo: LoginInfo, authInfo: PasswordInfo) =
    update(loginInfo, authInfo)

  def find(loginInfo: LoginInfo) =
    UserRepository.getByEmail(loginInfo) match {
      case Some(user) if user.emailConfirmed => Future.successful(Some(user.password))
      case _ => Future.successful(None)
    }

  def remove(loginInfo: LoginInfo) = Future.successful(UserRepository.remove(loginInfo))

  def save(loginInfo: LoginInfo, authInfo: PasswordInfo) =
    find(loginInfo).flatMap {
      case Some(_) => update(loginInfo, authInfo)
      case None => add(loginInfo, authInfo)
    }

  def update(loginInfo: LoginInfo, authInfo: PasswordInfo) =
    UserRepository.getByEmail(loginInfo) match {
      case Some(user) => {
        val newUser = user.copy(password = authInfo)
        UserRepository.save(newUser)
        Future.successful(authInfo)
      }
      case _ => {
        throw new Exception("PasswordInfoDAO - update : the user must exists to update its password")
      }
    }

}