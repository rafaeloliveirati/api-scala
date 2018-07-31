package controllers

import akka.actor.ActorSystem
import javax.inject._
import models.User
import net.liftweb.json.Serialization.write
import net.liftweb.json._
import play.api.mvc._
import services.UserService

@Singleton
class UserController @Inject()(system: ActorSystem, cc: ControllerComponents) extends AbstractController(cc) {

  implicit val formats = DefaultFormats

  def findUsers = Action {
    Ok(write(UserService.findUsers()))
  }

  def findUsersById(userId: String) = Action {
    Ok(write(UserService.findUsersById(userId)))
  }

  def saveUser = Action { request =>
    implicit val formats = DefaultFormats
    request.body.asJson.map { json =>
      //Refatorar Jogar em uma funcao
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
