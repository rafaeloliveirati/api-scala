package models

import config.MongoFactory
import org.bson.types.ObjectId

case class Purchase(_id: ObjectId = new ObjectId, transaction: String, value: Double, user: User, template: Template) {
}

object Purchase {

  def savePurchase(purchase: Purchase): Unit = {
    MongoFactory.purchaseCollection.insertOne(purchase)
  }

}