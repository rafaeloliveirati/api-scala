package models

import akka.actor.Actor
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.commons.TypeImports.DBObject
import config.MongoFactory
import org.bson.types.ObjectId
import play.api.Logger
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import play.api.libs.json._

case class User(name: String, email: String, document: String, password: String) {
  val _id: ObjectId = new ObjectId
}

object User {

  def save(user: User) {
    val mongoObj = buildMongoDbObject(user)
    MongoFactory.userCollection.save(mongoObj)
  }

  def remove(userId: String) {
    val query = MongoDBObject("_id" -> new ObjectId(userId))
    MongoFactory.userCollection.findAndRemove(query)
  }

  def update(user: User) {
    val mongoObj = buildMongoDbObject(user)
    val query = MongoDBObject("email" -> user.email)
    MongoFactory.userCollection.findAndModify(query, mongoObj)
  }

  def findUsers(): List[User] = {
    val mongoDBObjects = MongoFactory.userCollection.find().toList
    mongoDBObjects.map(dbObject => parseDbObjectToUser(dbObject).get)
  }

  def findById(userId: String): Option[User] = {
    val query = MongoDBObject("_id" -> new ObjectId(userId))
    val dbObject = MongoFactory.userCollection.findOne(query).get
    parseDbObjectToUser(dbObject)
  }

  implicit val userWrites: Writes[User] = (
    (__ \ "name").write[String] and
      (__ \ "email").write[String] and
      (__ \ "document").write[String] and
      (__ \ "password").write[String]
    ) (unlift(User.unapply))

  implicit val userReads: Reads[User] = (
    (__ \ "name").read[String] and
      (__ \ "email").read[String] and
      (__ \ "document").read[String] and
      (__ \ "password").read[String]
    ) (User.apply _)

  def parseDbObjectToUser(dbObject: DBObject): Option[User] = {
    val json = Json.parse(dbObject.toString)
    json.validate[User] match {
      case user: JsSuccess[User] => {
        Some(user.get)
      }
      case _: JsError => {
        Logger.info("Error parsing User")
        None
      }
    }
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

class UserActor extends Actor {
  def receive = {
    case _ =>
      sender() ! "Hello, "
    case message: String =>
      println(s"Message received from ${sender.path.name}, message = $message")
    case User => println("Response when forwarded by USER")
  }
}

