package services

import models.Template

import scala.concurrent.Future

object TemplateService {

  def saveTemplate(template: Template): Unit = {
    Template.save(template)
  }

  def findById(templateId: String): Future[Seq[Template]] = {
    Template.findById(templateId)
  }

  def findTemplates(): Future[Seq[Template]] = {
    Template.findTemplates()
  }
}
