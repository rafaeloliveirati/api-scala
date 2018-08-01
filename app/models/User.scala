package models

import config.MongoFactory
import org.bson.types.ObjectId
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.{MongoClient, MongoCollection, MongoDatabase}

import scala.concurrent.Future

case class User(_id: ObjectId = new ObjectId, name: String, email: String, document: String, password: String) {
}

object User {

  def save(user: User) {
    MongoFactory.userCollection.insertOne(user)
  }

  def remove(id: String) {
    MongoFactory.userCollection.deleteOne(equal("_id", id))
  }

  def findUsers(): Future[Seq[User]] = {
    MongoFactory.userCollection.find().toFuture()
  }

  def findById(userId: String): Future[Seq[User]] = {
    MongoFactory.userCollection.find(equal("_id", userId)).toFuture()
  }

}

