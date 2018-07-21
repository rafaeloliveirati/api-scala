package config

import com.mongodb.casbah.MongoConnection

object MongoFactory {
  private val SERVER = "localhost"
  private val PORT = 27017
  private val DATABASE = "scala"
  private val COLLECTION = "user"
  val connection = MongoConnection(SERVER)
  val collection = connection(DATABASE)(COLLECTION)
}