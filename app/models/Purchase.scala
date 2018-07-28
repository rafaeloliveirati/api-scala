package models

import com.mongodb.casbah.Imports.ObjectId
import config.MongoFactory
import utils.ConverterUtils

case class Purchase(
                     _id: ObjectId = new ObjectId,
                     transaction: String,
                     value: Double,
                     user: User,
                     template: Template
                   )

object Purchase {

  def savePurchase(purchase: Purchase): Unit = {
    val mongoObj = ConverterUtils.buildMongoDbObjectToPurchase(purchase)
    MongoFactory.purchaseCollection.save(mongoObj)
    println("salvou")
  }

}