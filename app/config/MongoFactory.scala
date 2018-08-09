package config

import com.mongodb.casbah.MongoConnection

object MongoFactory extends Config {
  private val connection = MongoConnection(serviceHost)
  val userCollection = connection(database)(userCollectionConf)
  val purchaseCollection = connection(database)(purchaseCollectionConf)
  val templateCollection = connection(database)(templateCollectionConf)
}