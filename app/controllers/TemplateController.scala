package controllers

import akka.actor.ActorSystem
import javax.inject._
import models.Template
import play.api.Logger
import play.api.libs.json.{JsError, JsSuccess, Json}
import play.api.mvc._
import services.TemplateService

import scala.concurrent.Future

@Singleton
class TemplateController @Inject()(system: ActorSystem, cc: ControllerComponents) extends AbstractController(cc) {

  def saveTemplate: Action[AnyContent] = Action.async { implicit request =>
    request.body.asJson.get.validate[Template] match {
      case template: JsSuccess[Template] => {
        TemplateService.saveTemplate(template.get)
        Future.successful(Ok(s"Template ${template.get.name} added successfully!"))
      }
      case e: JsError => {
        Logger.info("Error parsing Template")
        Future.successful(Ok("Error parsing Template!"))
      }
    }
  }

  def findTemplateById(templateId: String): Action[AnyContent] = Action.async {
    val template = TemplateService.findById(templateId).getOrElse(throw new Exception("Template not found"))
    Future.successful(Ok(Json.toJson(template)))
  }

  def findTemplates: Action[AnyContent] = Action.async {
    val templates = TemplateService.findTemplates()
    Future.successful(Ok(Json.toJson(templates)))
  }

}
