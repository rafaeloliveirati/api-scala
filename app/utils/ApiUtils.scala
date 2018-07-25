package utils

import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write

object ApiUtils {
  implicit val formats = DefaultFormats

  def convertToJson(obj: Object): String = {
    val jsonString = write(obj)
    jsonString
  }
}
