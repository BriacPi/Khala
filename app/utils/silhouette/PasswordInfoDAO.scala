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

  def add(loginInfo: LoginInfo, authInfo: PasswordInfo): Future[PasswordInfo] =
    update(loginInfo, authInfo)

  def find(loginInfo: LoginInfo): Future[Option[PasswordInfo]] =
    UserRepository.getByEmail(loginInfo).map {
      case Some(user) if user.emailConfirmed => Some(user.password)
      case _ => None
    }

  def remove(loginInfo: LoginInfo): Future[Unit] = Future{UserRepository.remove(loginInfo)}

  def save(loginInfo: LoginInfo, authInfo: PasswordInfo): Future[PasswordInfo] =
  find(loginInfo).flatMap {
    case Some(_) => update(loginInfo, authInfo)
    case None => add(loginInfo, authInfo)
  }

  def update(loginInfo: LoginInfo, authInfo: PasswordInfo): Future[PasswordInfo] =
    UserRepository.getByEmail(loginInfo).map {
      case Some(user) => {

        val newUser = user.copy(password = authInfo)
        println(user.password)
        UserRepository.save(newUser)
        println(authInfo)
        authInfo

      }
      case _ => {
        println("caca")
        throw new Exception("PasswordInfoDAO - update : the user must exists to update its password")
      }
    }

}