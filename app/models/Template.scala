package models

import com.mongodb.casbah.Imports.{MongoDBObject, ObjectId}
import config.MongoFactory
import utils.ConverterUtils

case class Template(
                    _id: ObjectId = new ObjectId,
                     name: String,
                     price: Double,
                     status: String
                   )

object Template {

  def findById(id: String): Template = {
    val query = MongoDBObject("_id" -> new ObjectId(id))
    val result = MongoFactory.templateCollection.findOne(query).get
    ConverterUtils.convertMongoDBObjectToTemplate(result)
  }

  def save(template: Template) {
    val mongoObj = ConverterUtils.buildTemplateToMongoDbObject(template)
    MongoFactory.templateCollection.save(mongoObj)
  }

  def findTemplates(): List[Template] = {
    val mongoDBObjects = MongoFactory.templateCollection.find().toList
    mongoDBObjects.map(mongo => ConverterUtils.convertDBObjectToTemplate(mongo))
  }
}