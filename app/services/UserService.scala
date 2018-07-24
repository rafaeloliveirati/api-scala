package services

import models.User

import scala.collection.mutable.ListBuffer


object UserService {

  def saveUser(user: User): Unit = {
    User.save(user)
  }

  def findUsers(): ListBuffer[User] = {
    User.findUsers()
  }

  def findUsersById(userId: String): User = {
    User.findUsersById(userId)
  }

  def removeUser(userId: String): Unit = {
    User.remove(userId)
  }

  def updateUser(user: User): Unit = {
    User.update(user)
  }
}
