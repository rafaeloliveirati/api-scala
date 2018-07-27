package config

import com.mongodb.casbah.MongoConnection

object MongoFactory {
  private val SERVER = "localhost"
  private val PORT = 27017
  private val DATABASE = "scala"
  private val USER_COLLECTION = "user"
  private val PURCHASE_COLLECTION = "purchase"
  private val TEMPLATE_COLLECTION = "template"
  val connection = MongoConnection(SERVER)
  val userCollection = connection(DATABASE)(USER_COLLECTION)
  val purchaseCollection = connection(DATABASE)(PURCHASE_COLLECTION)
  val templateCollection = connection(DATABASE)(TEMPLATE_COLLECTION)
}