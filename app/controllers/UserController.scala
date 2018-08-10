package controllers

import akka.actor._
import javax.inject._
import models.UserActorObject.FIND_USER
import models.{User, UserActorObject}
import play.api.Logger
import play.api.libs.json.{JsError, JsSuccess, Json}
import play.api.mvc._
import services.UserService

import scala.concurrent._

@Singleton
class UserController @Inject()(system: ActorSystem, cc: ControllerComponents) extends AbstractController(cc) {

  def findUsers: Action[AnyContent] = Action.async {
    UserActorObject.userActor ! FIND_USER

    //    val users = UserService.findUsers
    //    Future.successful(Ok(Json.toJson(users)))
    Future.successful(Ok("fdasfds"))
  }

  def findUsersById(userId: String): Action[AnyContent] = Action.async {
    val user = UserService.findUsersById(userId).getOrElse(throw new Exception("User not found"))
    Future.successful(Ok(Json.toJson(user)))
  }

  def saveUser: Action[AnyContent] = Action.async { implicit request =>
    request.body.asJson.get.validate[User] match {
      case user: JsSuccess[User] => {
        UserService.saveUser(user.get)
        Future.successful(Ok(s"User ${user.get.name} added successfully!"))
      }
      case e: JsError => {
        Logger.info("Error parsing User")
        Future.successful(Ok("Error parsing User!"))
      }
    }
  }

  def removeUser(userId: String): Action[AnyContent] = Action.async {
    UserService.removeUser(userId)
    Future.successful(Ok(s"User ${userId} removed successfully!"))
  }

}
