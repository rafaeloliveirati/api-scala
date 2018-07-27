package controllers

import akka.actor.ActorSystem
import javax.inject._
import play.api.mvc._
import services.PurchaseService

@Singleton
class PurchaseController @Inject()(system: ActorSystem, cc: ControllerComponents) extends AbstractController(cc) {

  def savePurchase = Action {
    PurchaseService.savePurchase()
    Ok("fdsafds")
  }


}
