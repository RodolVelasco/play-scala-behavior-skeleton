package com.applaudo.services.poc

object ReadingFromConfig extends App {

  import com.typesafe.config.ConfigFactory

  val config = ConfigFactory.load()
  val carpetaOrigen = config.getString("app.folder.source")

  println(carpetaOrigen.toString)
}
