package utils

import models.User
import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write

import scala.collection.mutable.ListBuffer

object ApiUtils {
  implicit val formats = DefaultFormats

  def convertToJson(users: ListBuffer[User]): String = {
    val jsonString = write(users)
    jsonString
  }
}
