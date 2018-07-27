package models

import com.mongodb.casbah.Imports._
import config.MongoFactory
import utils.ApiUtils

object UserHandler {

  case class User(name: String, email: String, document: String, password: String)

  val collection = MongoFactory.collection

  def save(user: User) {
    val mongoObj = ApiUtils.buildMongoDbObject(user)
    collection.save(mongoObj)
  }

  def remove(userId: String) {
    val query = MongoDBObject("_id" -> new ObjectId(userId))
    collection.findAndRemove(query)
  }

  def update(user: User) {
    val mongoObj = ApiUtils.buildMongoDbObject(user)
    val query = MongoDBObject("email" -> user.email)
    collection.findAndModify(query, mongoObj)
  }

  def findUsers(): List[User] = {
    val mongoDBObjects = collection.find().toList
    mongoDBObjects.map(mongo => ApiUtils.convertDBObject(mongo))
  }

  def findUsersById(userId: String): User = {
    val query = MongoDBObject("email" -> new ObjectId(userId))
    val result = collection.findOne(query).get
    ApiUtils.convertMongoDBObject(result)
  }

}