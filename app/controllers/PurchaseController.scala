package controllers

import akka.actor.ActorSystem
import javax.inject._
import play.api.mvc._
import services.PurchaseService

@Singleton
class PurchaseController @Inject()(system: ActorSystem, cc: ControllerComponents) extends AbstractController(cc) {

  def savePurchase = Action { request =>
    val json = request.body.asJson
    val userId = json.get("userId").toString().replace("\"", "")
    val templateId = json.get("templateId").toString().replace("\"", "")
    PurchaseService.savePurchase(userId, templateId)
    Ok(s"Purchase add success!")
  }


}
