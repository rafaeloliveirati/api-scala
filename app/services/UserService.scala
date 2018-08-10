package services

import models.User


object UserService {

  def saveUser(user: User): Unit = {
    User.save(user)
  }

  def findUsers: List[User] = {
    User.findUsers
  }

  def findUsersById(userId: String): Option[User] = {
    User.findById(userId)
  }

  def removeUser(userId: String): Unit = {
    User.remove(userId)
  }

  def updateUser(user: User): Unit = {
    User.update(user)
  }
}
