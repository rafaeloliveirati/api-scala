package models

import com.mongodb.casbah.Imports._
import config.MongoFactory

class User(val name: String, val email: String, val document: String, val password: String)

object User {

  def save(user: User) {
    val mongoObj = buildMongoDbObject(user)
    MongoFactory.collection.save(mongoObj)
  }

  def remove(user: User) {
    val mongoObj = buildMongoDbObject(user)
    MongoFactory.collection.remove(mongoObj)
  }

  def update(user: User) {
    val mongoObj = buildMongoDbObject(user)
    var query = MongoDBObject("email" -> user.email)
    MongoFactory.collection.findAndModify(query, mongoObj)
  }

  private def buildMongoDbObject(user: User): MongoDBObject = {
    val builder = MongoDBObject.newBuilder
    builder += "name" -> user.name
    builder += "email" -> user.email
    builder += "document" -> user.document
    builder += "password" -> user.password
    builder.result
  }
}