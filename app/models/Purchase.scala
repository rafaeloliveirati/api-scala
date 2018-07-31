package models

import com.mongodb.casbah.Imports.{MongoDBObject, ObjectId}
import com.mongodb.casbah.commons.TypeImports.DBObject
import config.MongoFactory
import play.api.libs.functional.syntax.{unlift, _}
import play.api.libs.json.{Json, Reads, Writes, __}

case class Purchase(
                     transaction: String,
                     value: Double,
                     user: User,
                     template: Template
                   ) {
  val _id: ObjectId = new ObjectId
}

object Purchase {

  def savePurchase(purchase: Purchase): Unit = {
    val mongoObj = buildMongoDbObjectToPurchase(purchase)
    MongoFactory.purchaseCollection.save(mongoObj)
  }

  implicit val purchaseWrites: Writes[Purchase] = (
    (__ \ "transaction").write[String] and
      (__ \ "value").write[Double] and
      (__ \ "user").write[User] and
      (__ \ "template").write[Template]
    ) (unlift(Purchase.unapply))

  implicit val purchaseReads: Reads[Purchase] = (
    (__ \ "transaction").read[String] and
      (__ \ "value").read[Double] and
      (__ \ "user").read[User] and
      (__ \ "template").read[Template]
    ) (Purchase.apply _)

  def parseDbObjectToUser(dbObject: DBObject): Purchase = {
    purchaseReads.reads(Json.parse(dbObject.toString)).get
  }

  def buildMongoDbObjectToPurchase(purchase: Purchase): DBObject = {
    val builder = MongoDBObject.newBuilder
    builder += "transaction" -> purchase.transaction
    builder += "value" -> purchase.value
    builder += "user" -> purchase.user
    builder += "template" -> purchase.template
    builder.result
  }
}