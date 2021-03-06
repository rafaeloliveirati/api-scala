name := "nuvemshop"

version := "1.0"

lazy val `nuvemshop` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

scalaVersion := "2.12.2"

libraryDependencies ++= {
  val liftVersion = "3.3.0"
  Seq(ehcache, ws, specs2 % Test, guice,
    "org.mongodb" %% "casbah" % "3.1.1",
    "org.slf4j" % "slf4j-simple" % "1.6.4",
    "com.google.code.gson" % "gson" % "2.2.4",
    "net.liftweb" %% "lift-webkit" % liftVersion % "compile",
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    "com.typesafe.akka" %% "akka-actor" % "2.5.14"
  )
}

unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")

