package models

import com.mongodb.casbah.Imports._
import config.MongoFactory
import utils.ConverterUtils

case class User(
                 _id: ObjectId = new ObjectId,
                 name: String,
                 email: String,
                 document: String,
                 password: String
               )

object User {

  def save(user: User) {
    val mongoObj = ConverterUtils.buildMongoDbObject(user)
    MongoFactory.userCollection.save(mongoObj)
    println("fdsafdsa")
  }

  def remove(userId: String) {
    val query = MongoDBObject("_id" -> new ObjectId(userId))
    MongoFactory.userCollection.findAndRemove(query)
  }

  def update(user: User) {
    val mongoObj = ConverterUtils.buildMongoDbObject(user)
    val query = MongoDBObject("email" -> user.email)
    MongoFactory.userCollection.findAndModify(query, mongoObj)
  }

  def findUsers(): List[User] = {
    val mongoDBObjects = MongoFactory.userCollection.find().toList
    mongoDBObjects.map(mongo => ConverterUtils.convertDBObject(mongo))
  }

  def findById(userId: String): User = {
    val query = MongoDBObject("_id" -> new ObjectId(userId))
    val result = MongoFactory.userCollection.findOne(query).get
    ConverterUtils.convertMongoDBObject(result)
  }

}