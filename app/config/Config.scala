package config

import com.typesafe.config.ConfigFactory

import scala.util.Try

trait Config {
  private val config = ConfigFactory.load()

  lazy val serviceHost = Try(config.getString("database.hostname")).getOrElse("localhost")

  lazy val servicePort = Try(config.getString("database.port")).getOrElse(9090)

  lazy val database = Try(config.getString("database.db")).toOption.orNull

  lazy val userCollectionConf = Try(config.getString("database.collections.user")).toOption.orNull
  lazy val purchaseCollectionConf = Try(config.getString("database.collections.purchase")).toOption.orNull
  lazy val templateCollectionConf = Try(config.getString("database.collections.template")).toOption.orNull
}
