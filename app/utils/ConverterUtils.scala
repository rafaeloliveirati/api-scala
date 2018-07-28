package utils

import com.mongodb.DBObject
import com.mongodb.casbah.Imports._
import models.{Purchase, Template, User}
import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write

object ConverterUtils {
  implicit val formats = DefaultFormats

  def convertToJson(obj: Object): String = {
    val jsonString = write(obj)
    jsonString
  }


  def convertMongoDBObject(mongoDBObject: MongoDBObject): User = {
    User(
      mongoDBObject.getAs[ObjectId]("_id").get,
      mongoDBObject.getAs[String]("name").get,
      mongoDBObject.getAs[String]("email").get,
      mongoDBObject.getAs[String]("document").get,
      mongoDBObject.getAs[String]("password").get
    )
  }

  def convertMongoDBObjectToTemplate(mongoDBObject: DBObject): Template = {
    Template(
      mongoDBObject.getAs[ObjectId]("_id").get,
      mongoDBObject.getAs[String]("name").get,
      mongoDBObject.getAs[Double]("price").get,
      mongoDBObject.getAs[String]("status").get
    )
  }

  def buildMongoDbObject(user: User): MongoDBObject = {
    val builder = MongoDBObject.newBuilder
    builder += "name" -> user.name
    builder += "email" -> user.email
    builder += "document" -> user.document
    builder += "password" -> user.password
    builder.result
  }

  def buildMongoDbObjectToPurchase(purchase: Purchase): DBObject = {
    val builder = MongoDBObject.newBuilder
    builder += "transaction" -> purchase.transaction
    builder += "value" -> purchase.value
    builder += "user" -> purchase.user
    builder += "template" -> purchase.template
    builder.result
  }

  def convertDBObject(mongoDBObject: DBObject): User = {
    User(
      mongoDBObject.getAs[ObjectId]("_id").get,
      mongoDBObject.getAs[String]("name").get,
      mongoDBObject.getAs[String]("email").get,
      mongoDBObject.getAs[String]("document").get,
      mongoDBObject.getAs[String]("password").get
    )
  }

  def convertDBObjectToTemplate(mongoDBObject: DBObject): Template = {
    Template(
      mongoDBObject.getAs[ObjectId]("_id").get,
      mongoDBObject.getAs[String]("name").get,
      mongoDBObject.getAs[Double]("price").get,
      mongoDBObject.getAs[String]("status").get
    )
  }

  def buildTemplateToMongoDbObject(template: Template): DBObject = {
    val builder = MongoDBObject.newBuilder
    builder += "name" -> template.name
    builder += "price" -> template.price
    builder += "status" -> template.status
    builder.result
  }
}
