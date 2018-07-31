package models

import com.mongodb.casbah.Imports.{MongoDBObject, ObjectId}
import com.mongodb.casbah.commons.TypeImports.DBObject
import config.MongoFactory
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.Reads._
import play.api.libs.json.{Json, Reads, Writes, __}

case class Template(name: String, price: Double, status: String) {
  val _id: ObjectId = new ObjectId
}

object Template {

  def findById(id: String): Template = {
    val query = MongoDBObject("_id" -> new ObjectId(id))
    val dbObject = MongoFactory.templateCollection.findOne(query).get
    parseDbObjectToTemplate(dbObject)
  }

  def save(template: Template) {
    val mongoObj = buildTemplateToMongoDbObject(template)
    MongoFactory.templateCollection.save(mongoObj)
  }

  def findTemplates(): List[Template] = {
    val mongoDBObjects = MongoFactory.templateCollection.find().toList
    mongoDBObjects.map(dbObject => parseDbObjectToTemplate(dbObject))
  }

  implicit val templatesWrites: Writes[Template] = (
    (__ \ "name").write[String] and
      (__ \ "price").write[Double] and
      (__ \ "status").write[String]
    ) (unlift(Template.unapply))

  implicit val templateReads: Reads[Template] = (
    (__ \ "name").read[String] and
      (__ \ "price").read[Double] and
      (__ \ "status").read[String]
    ) (Template.apply _)

  def parseDbObjectToTemplate(dbObject: DBObject): Template = {
    templateReads.reads(Json.parse(dbObject.toString)).get
  }

  def buildTemplateToMongoDbObject(template: Template): DBObject = {
    val builder = MongoDBObject.newBuilder
    builder += "name" -> template.name
    builder += "price" -> template.price
    builder += "status" -> template.status
    builder.result
  }
}