package controllers

import javax.inject._
import models.User
import play.api.mvc._
import services.UserService
import utils.ApiUtils

@Singleton
class UserController @Inject()(cc: ControllerComponents, user: String) extends AbstractController(cc) {

  def findUsers = Action {
    Ok(ApiUtils.convertListToJson(UserService.findUsers()))
  }

  def findUsersById(userId: String) = Action {
    Ok(ApiUtils.convertObjectToJson(UserService.findUsersById(userId)))
  }

  def saveUser = Action {
    val user = new User("Rafael", "rafaoliveira.ti@gmail.com", "09194441642", "123")
    UserService.saveUser(user)
    Ok("ok")
  }

  def removeUser(userId: String) = Action {
    println(userId)
    val user = new User("Rafael", "rafaoliveira.ti@gmail.com", "09194441642", "123")
    UserService.removeUser(userId)
    Ok("ok")
  }

}
