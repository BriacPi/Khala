package utils.silhouette

import models.User
import Implicits._
import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.api.services.IdentityService
import repositories.UserRepository
import scala.concurrent.Future

class UserService extends IdentityService[User] {
  def retrieve(loginInfo: LoginInfo): Future[Option[User]] = UserRepository.getByEmail(loginInfo)
  def save(user: User): Future[User] = UserRepository.save(user)
}