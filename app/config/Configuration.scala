package config

import com.typesafe.config.ConfigFactory

import scala.util.Try

trait Configuration {
  private val config = ConfigFactory.load()

  lazy val serviceHost = Try(config.getString("mongo.hostname")).getOrElse("localhost")

  lazy val servicePort = Try(config.getString("mongo.port")).getOrElse(9090)

  lazy val database = Try(config.getString("mongo.db")).toOption.orNull

  lazy val userCollectionConf = Try(config.getString("mongo.collections.user")).toOption.orNull
  lazy val purchaseCollectionConf = Try(config.getString("mongo.collections.purchase")).toOption.orNull
  lazy val templateCollectionConf = Try(config.getString("mongo.collections.template")).toOption.orNull
}
