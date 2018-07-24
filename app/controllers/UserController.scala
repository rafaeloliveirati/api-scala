package controllers

import javax.inject._
import models.User
import play.api.mvc._
import services.UserService
import utils.ApiUtils

@Singleton
class UserController @Inject()(cc: ControllerComponents, user: String) extends AbstractController(cc) {

  def findUsers = Action {
    Ok(ApiUtils.convertToJson(UserService.findAll()))
  }

  def saveUser = Action {
    val user = new User("Rafael", "rafaoliveira.ti@gmail.com", "09194441642", "123")
    UserService.saveUser(user)
    Ok("ok")
  }

  def removeUser = Action {
    val user = new User("Rafael", "rafaoliveira.ti@gmail.com", "09194441642", "123")
    UserService.saveUser(user)
    Ok("ok")
  }

}
