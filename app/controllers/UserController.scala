package controllers

import akka.actor.ActorSystem
import javax.inject._
import models.User
import net.liftweb.json._
import play.api.libs.json.Json
import play.api.mvc._
import services.UserService

@Singleton
class UserController @Inject()(system: ActorSystem, cc: ControllerComponents) extends AbstractController(cc) {

  def findUsers = Action {
    Ok(Json.toJson(UserService.findUsers()))
  }

  def findUsersById(userId: String) = Action {
    Ok(Json.toJson(UserService.findUsersById(userId)))
  }

  def saveUser = Action { request =>
    implicit val formats = DefaultFormats
    val jValue = JsonParser.parse(request.body.asJson.get.toString())
    val user = jValue.extract[User]
    UserService.saveUser(user)
    Ok(s"User ${user.name} add with success!")
  }

  def removeUser(userId: String) = Action {
    println(userId)
    UserService.removeUser(userId)
    Ok("ok")
  }

}
