package controllers

import akka.actor.ActorSystem
import javax.inject._
import models.Template
import net.liftweb.json.{DefaultFormats, JsonParser}
import play.api.libs.json.Json
import play.api.mvc._
import services.TemplateService

import scala.concurrent.Future

@Singleton
class TemplateController @Inject()(system: ActorSystem, cc: ControllerComponents) extends AbstractController(cc) {

  def saveTemplate: Action[AnyContent] = Action.async { implicit request =>
    implicit val formats = DefaultFormats
    val jValue = JsonParser.parse(request.body.asJson.get.toString())
    val template = jValue.extract[Template]
    TemplateService.saveTemplate(template)
    Future.successful(Ok(s"Template ${template.name} added successfully!"))
  }

  def findTemplateById(templateId: String): Action[AnyContent] = Action.async {
    Future.successful(Ok(Json.toJson(TemplateService.findById(templateId))))
  }

  def findTemplates: Action[AnyContent] = Action.async {
    Future.successful(Ok(Json.toJson(TemplateService.findTemplates())))
    )
  }

}
