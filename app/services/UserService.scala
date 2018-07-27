package services

import models.{User, UserHandler}


object UserService {

  def saveUser(user: User): Unit = {
    UserHandler.save(user)
  }

  def findUsers(): List[User] = {
    UserHandler.findUsers()
  }

  def findUsersById(userId: String): User = {
    UserHandler.findById(userId)
  }

  def removeUser(userId: String): Unit = {
    UserHandler.remove(userId)
  }

  def updateUser(user: User): Unit = {
    UserHandler.update(user)
  }
}
