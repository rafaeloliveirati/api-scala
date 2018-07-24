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

  def this(mongoDBObject: DBObject) {
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
    val query = MongoDBObject("_id" -> new ObjectId(userId))
    val result = collection.findAndRemove(query)
    println("result: " + result)
  }

  def update(user: User) {
    val mongoObj = buildMongoDbObject(user)
    val query = MongoDBObject("email" -> user.email)
    collection.findAndModify(query, mongoObj)
  }

  def findUsers(): ListBuffer[User] = {
    val mongoDBObjects = collection.find().toList
    val users = mutable.ListBuffer.empty[User]
    mongoDBObjects.foreach(user => {
      users += new User(user)
    })
    users
  }

  def findUsersById(userId: String): User = {
    val query = MongoDBObject("_id" -> new ObjectId(userId))
    val result = collection.findOne(query)
    println(result)
    val user = new User("Rafael", "rafaoliveira.ti@gmail.com", "09194441642", "123")
    user
    //    val user = new User(result)
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