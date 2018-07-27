package services

import models.{Template, TemplateHandler}

object TemplateService {

  def saveTemplate(template: Template): Unit = {
    TemplateHandler.save(template)
  }

  def findById(templateId: String): Template = {
    TemplateHandler.findById(templateId)
  }

  def findTemplates(): List[Template] = {
    TemplateHandler.findTemplates()
  }
}
