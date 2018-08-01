package models

import config.MongoFactory
import org.bson.types.ObjectId
import org.mongodb.scala.model.Filters.equal

import scala.concurrent.Future

case class Template(_id: ObjectId = new ObjectId, name: String, price: Double, status: String) {
}

object Template {

  def findById(id: String): Future[Seq[Template]] = {
    MongoFactory.templateCollection.find(equal("_id", id)).toFuture()
  }

  def save(template: Template) {
    MongoFactory.templateCollection.insertOne(template)
  }

  def findTemplates(): Future[Seq[Template]] = {
    MongoFactory.templateCollection.find().toFuture()
  }

}