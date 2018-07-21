package services

import models.User


object UserService {

  def saveUser(user: User): Unit = {
    User.save(user)
  }

  def removeUser(user: User): Unit = {
    User.remove(user)
  }

  def updateUser(user: User): Unit = {
    User.update(user)
  }
}
