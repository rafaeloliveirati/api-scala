package controllers

import actors.UserActor
import akka.actor.{ActorSystem, Props}
import javax.inject._
import models.User
import net.liftweb.json._
import play.api.libs.json.Json
import play.api.mvc._
import services.UserService

@Singleton
class UserController @Inject()(system: ActorSystem, cc: ControllerComponents) extends AbstractController(cc) {

  def findUsers = Action {
    val userActor = system.actorOf(Props[UserActor], "userActor")
    userActor ! "hey there"
    //Json.toJson(UserService.findUsers())
    Ok("fdsfds")
  }

  def findUsersById(userId: String) = Action {
    //Json.toJson(UserService.findUsersById(userId))
    Ok("dsfdas")
  }

  def saveUser = Action { request =>
    implicit val formats = DefaultFormats
    val jValue = JsonParser.parse(request.body.asJson.get.toString())
    val user = jValue.extract[User]
    UserService.saveUser(user)
    Ok(s"User ${user.name} add with success!")
  }

  def removeUser(userId: String) = Action {
    UserService.removeUser(userId)
    Ok("ok")
  }

}
