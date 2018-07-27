package controllers

import javax.inject._
import models.UserHandler.User
import net.liftweb.json._
import play.api.mvc._
import services.UserService
import utils.ApiUtils

@Singleton
class UserController @Inject()(cc: ControllerComponents, user: String) extends AbstractController(cc) {

  def findUsers = Action {
    Ok(ApiUtils.convertToJson(UserService.findUsers()))
  }

  def findUsersById(userId: String) = Action {
    Ok(ApiUtils.convertToJson(UserService.findUsersById(userId)))
  }

  def saveUser = Action { request =>
    implicit val formats = DefaultFormats
    request.body.asJson.map { json =>
      val jValue = JsonParser.parse(json.toString())
      val user = jValue.extract[User]
      UserService.saveUser(user)
      Ok(s"Usuario ${user.name} cadastrado com sucesso!")
    }.getOrElse {
      BadRequest("Expecting application/json request body")
    }
  }

  def removeUser(userId: String) = Action {
    println(userId)
    UserService.removeUser(userId)
    Ok("ok")
  }

}
