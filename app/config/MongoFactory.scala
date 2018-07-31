package config

import com.mongodb.casbah.MongoConnection
import com.typesafe.config.ConfigFactory

object MongoFactory {
  private val config = ConfigFactory.load()
  private val SERVER = config.getString("mongo.hostname")
  private val PORT = config.getString("mongo.port")
  private val DATABASE = config.getString("mongo.db")
  private val USER_COLLECTION = config.getString("mongo.collections.user")
  private val PURCHASE_COLLECTION = config.getString("mongo.collections.purchase")
  private val TEMPLATE_COLLECTION = config.getString("mongo.collections.template")
  private val connection = MongoConnection(SERVER)
  val userCollection = connection(DATABASE)(USER_COLLECTION)
  val purchaseCollection = connection(DATABASE)(PURCHASE_COLLECTION)
  val templateCollection = connection(DATABASE)(TEMPLATE_COLLECTION)
}