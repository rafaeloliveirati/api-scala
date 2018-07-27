package controllers

import akka.actor.ActorSystem
import javax.inject._
import models.{Template, TemplateHandler, User, UserHandler}
import net.liftweb.json.{DefaultFormats, JsonParser}
import play.api.mvc._
import services.{TemplateService, UserService}
import utils.ConverterUtils

@Singleton
class TemplateController @Inject()(system: ActorSystem, cc: ControllerComponents) extends AbstractController(cc) {

  def saveTemplate = Action { request =>
    implicit val formats = DefaultFormats
    request.body.asJson.map { json =>
      //Refatorar Jogar em uma funcao
      val jValue = JsonParser.parse(json.toString())
      val template = jValue.extract[Template]
      TemplateService.saveTemplate(template)
      Ok(s"Usuario ${template.name} cadastrado com sucesso!")
    }.getOrElse {
      BadRequest("Expecting application/json request body")
    }
  }

  def findTemplateById(templateId: String) = Action {
    Ok(ConverterUtils.convertToJson(TemplateService.findById(templateId)))
  }

  def findTemplates = Action {
    Ok(ConverterUtils.convertToJson(TemplateService.findTemplates()))
  }

}
