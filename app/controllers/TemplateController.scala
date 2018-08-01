package controllers

import akka.actor.ActorSystem
import javax.inject._
import models.Template
import net.liftweb.json.{DefaultFormats, JsonParser}
import play.api.libs.json.Json
import play.api.mvc._
import services.TemplateService

@Singleton
class TemplateController @Inject()(system: ActorSystem, cc: ControllerComponents) extends AbstractController(cc) {

  def saveTemplate = Action { request =>
    implicit val formats = DefaultFormats
    val jValue = JsonParser.parse(request.body.asJson.get.toString())
    val template = jValue.extract[Template]
    TemplateService.saveTemplate(template)
    Ok(s"User ${template.name} add with success!")
  }

  def findTemplateById(templateId: String) = Action {
    //Json.toJson(TemplateService.findById(templateId))
    Ok("fdsfds")
  }

  def findTemplates = Action {
    //Json.toJson(TemplateService.findTemplates())
    Ok("fdafdsa")
  }

}
