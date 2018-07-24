package models

import com.mongodb.casbah.Imports._
import config.MongoFactory

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class User(val name: String, val email: String, val document: String, val password: String) {

  def this(mongoDBObject: MongoDBObject) {
    this(
      mongoDBObject.getAs[String]("name").get,
      mongoDBObject.getAs[String]("email").get,
      mongoDBObject.getAs[String]("document").get,
      mongoDBObject.getAs[String]("password").get
    );
  }
}

object User {
  val collection = MongoFactory.collection

  def save(user: User) {
    val mongoObj = buildMongoDbObject(user)
    collection.save(mongoObj)
  }

  def remove(userId: String) {
    var query = MongoDBObject("_id" -> new ObjectId(userId))
    var result = collection.findAndRemove(query)
    println("result: " + result)
  }

  def update(user: User) {
    val mongoObj = buildMongoDbObject(user)
    var query = MongoDBObject("email" -> user.email)
    collection.findAndModify(query, mongoObj)
  }

  def findAll(): ListBuffer[User] = {
    val mongoDBObjects = collection.find().toList
    var users = mutable.ListBuffer.empty[User]
    mongoDBObjects.foreach(user => {
      users += new User(user)
    })
    users
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