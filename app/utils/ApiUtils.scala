package utils

import models.User
import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write

import scala.collection.mutable.ListBuffer

object ApiUtils {
  implicit val formats = DefaultFormats

  def convertListToJson(users: ListBuffer[User]): String = {
    val jsonString = write(users)
    jsonString
  }

  def convertToJson(users: User): String = {
    val jsonString = write(users)
    jsonString
  }


  def convertObjectToJson(obj: Object): String = {
    val jsonString = write(obj)
    jsonString
  }
}
