package com.applaudo.services.modules

import javax.inject._
import akka.actor.{ActorSystem, Props}
import com.applaudo.services.orchestrator.flows.FileMover
import play.api.{Configuration, Logger}

@Singleton
class FileMoverStartup @Inject()(actorSystem: ActorSystem, configuration: Configuration) {

  val logger: Logger = Logger(this.getClass)

  logger.info("Initializing ...")

  // Inicializa tu actor aquí
  val fileMover = actorSystem.actorOf(Props(new FileMover(configuration)), name = "fileMover")
}

