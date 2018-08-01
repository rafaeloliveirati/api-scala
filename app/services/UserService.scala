package services

import models.User

import scala.concurrent.Future


object UserService {

  def saveUser(user: User): Unit = {
    User.save(user)
  }

  def findUsers(): Future[Seq[User]] = {
    User.findUsers()
  }

  def findUsersById(id: String): Future[Seq[User]] = {
    User.findById(id)
  }

  def removeUser(id: String): Unit = {
    User.remove(id)
  }

}
