package models

import com.mongodb.casbah.Imports._
import config.MongoFactory

object UserHandler {

  case class User(name: String, email: String, document: String, password: String)

  val collection = MongoFactory.collection

  def save(user: User) {
    val mongoObj = buildMongoDbObject(user)
    collection.save(mongoObj)
  }

  def remove(userId: String) {
    val query = MongoDBObject("_id" -> new ObjectId(userId))
    val result = collection.findAndRemove(query)
    println("result: " + result)
  }

  def update(user: User) {
    val mongoObj = buildMongoDbObject(user)
    val query = MongoDBObject("email" -> user.email)
    collection.findAndModify(query, mongoObj)
  }

  def findUsers(): List[User] = {
    val mongoDBObjects = collection.find().toList
    mongoDBObjects.map(mongo => convertDBObject(mongo))
  }

  def findUsersById(userId: String): User = {
    val query = MongoDBObject("email" -> new ObjectId(userId))
    val result = collection.findOne(query).get
    val user = convertMongoDBObject(result)
    user
  }

  def convertMongoDBObject(mongoDBObject: MongoDBObject): User = {
    User(
      mongoDBObject.getAs[String]("name").get,
      mongoDBObject.getAs[String]("email").get,
      mongoDBObject.getAs[String]("document").get,
      mongoDBObject.getAs[String]("password").get
    )
  }

  def convertDBObject(mongoDBObject: DBObject): User = {
    User(
      mongoDBObject.getAs[String]("name").get,
      mongoDBObject.getAs[String]("email").get,
      mongoDBObject.getAs[String]("document").get,
      mongoDBObject.getAs[String]("password").get
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

}