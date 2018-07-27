package models

import com.mongodb.casbah.Imports.{MongoDBObject, ObjectId}
import config.MongoFactory
import utils.ConverterUtils

case class Template(name: String, price: Double, status: String)

object TemplateHandler {

  private val templateCollection = MongoFactory.templateCollection

  def findById(id: String): Template = {
    val query = MongoDBObject("_id" -> new ObjectId(id))
    val result = templateCollection.findOne(query).get
    ConverterUtils.convertMongoDBObjectToTemplate(result)
  }

  def save(template: Template) {
    val mongoObj = ConverterUtils.buildTemplateToMongoDbObject(template)
    templateCollection.save(mongoObj)
  }

  def findTemplates(): List[Template] = {
    val mongoDBObjects = templateCollection.find().toList
    mongoDBObjects.map(mongo => ConverterUtils.convertDBObjectToTemplate(mongo))
  }
}