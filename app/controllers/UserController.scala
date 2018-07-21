package controllers

import com.google.gson.Gson
import javax.inject._
import models.User
import play.api.mvc._

@Singleton
class UserController @Inject()(cc: ControllerComponents, user: User) extends AbstractController(cc) {

  def findUsers = Action {
    val user = new User("Rafael", "rafaOliveira.ti@gmail.com", "09194441642", "123")
    val gson: Gson = new Gson()
    Ok(gson.toJson(user))
  }

}
