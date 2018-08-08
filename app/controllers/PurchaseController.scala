package controllers

import akka.actor.ActorSystem
import javax.inject._
import play.api.mvc._
import services.PurchaseService

import scala.concurrent.Future

@Singleton
class PurchaseController @Inject()(system: ActorSystem, cc: ControllerComponents) extends AbstractController(cc) {

  def savePurchase: Action[AnyContent] = Action.async { implicit request =>
    val json = request.body.asJson
    val userId = json.get("userId").toString().replace("\"", "")
    val templateId = json.get("templateId").toString().replace("\"", "")
    Future.successful(Ok(PurchaseService.savePurchase(userId, templateId)))
  }

}
