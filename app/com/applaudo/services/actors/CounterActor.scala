package com.applaudo.services.actors

import akka.actor.Actor
import play.api.Logger

class CounterActor extends Actor {

  val logger: Logger = Logger(this.getClass)

  logger.info("Initializing ...")

  var count = 0

  override def receive: Receive = {
    case "tick" =>
      count += 1
      println(s"Contando: $count")
  }
}
