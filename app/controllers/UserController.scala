package controllers

import akka.actor.ActorSystem
import javax.inject._
import models.User
import net.liftweb.json._
import play.api.libs.json.Json
import play.api.mvc._
import services.UserService

import scala.concurrent._

@Singleton
class UserController @Inject()(system: ActorSystem, cc: ControllerComponents) extends AbstractController(cc) {

  def findUsers: Action[AnyContent] = Action.async {
    Future.successful(Ok(Json.toJson(UserService.findUsers())))
  }

  def findUsersById(userId: String): Action[AnyContent] = Action.async {
    Future.successful(Ok(Json.toJson(UserService.findUsersById(userId))))
  }

  def saveUser: Action[AnyContent] = Action.async { implicit request =>
    implicit val formats = DefaultFormats
    val jValue = JsonParser.parse(request.body.asJson.get.toString())
    val user = jValue.extract[User]
    UserService.saveUser(user)
    Future.successful(Ok(s"User ${user.name} added successfully!"))
  }

  def removeUser(userId: String): Action[AnyContent] = Action.async {
    UserService.removeUser(userId)
    Future.successful(Ok(s"User ${userId} removed successfully!"))
  }

}
