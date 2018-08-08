package services

import models.Template

object TemplateService {

  def saveTemplate(template: Template): Unit = {
    Template.save(template)
  }

  def findById(templateId: String): Option[Template] = {
    Template.findById(templateId)
  }

  def findTemplates(): List[Template] = {
    Template.findTemplates()
  }
}
