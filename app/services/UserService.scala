package services

import models.User

import scala.collection.mutable.ListBuffer


object UserService {

  def saveUser(user: User): Unit = {
    User.save(user)
  }

  def findAll(): ListBuffer[User] = {
    User.findAll()
  }

  def removeUser(user: User): Unit = {
    User.remove(user)
  }

  def updateUser(user: User): Unit = {
    User.update(user)
  }
}
