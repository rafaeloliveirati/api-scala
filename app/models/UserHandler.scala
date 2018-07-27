package models

import com.mongodb.casbah.Imports._
import config.MongoFactory
import utils.ConverterUtils

case class User(name: String, email: String, document: String, password: String)

object UserHandler {

  private val userCollection = MongoFactory.userCollection

  def save(user: User) {
    val mongoObj = ConverterUtils.buildMongoDbObject(user)
    userCollection.save(mongoObj)
  }

  def remove(userId: String) {
    val query = MongoDBObject("_id" -> new ObjectId(userId))
    userCollection.findAndRemove(query)
  }

  def update(user: User) {
    val mongoObj = ConverterUtils.buildMongoDbObject(user)
    val query = MongoDBObject("email" -> user.email)
    userCollection.findAndModify(query, mongoObj)
  }

  def findUsers(): List[User] = {
    val mongoDBObjects = userCollection.find().toList
    mongoDBObjects.map(mongo => ConverterUtils.convertDBObject(mongo))
  }

  def findById(userId: String): User = {
    val query = MongoDBObject("_id" -> new ObjectId(userId))
    val result = userCollection.findOne(query).get
    ConverterUtils.convertMongoDBObject(result)
  }

}