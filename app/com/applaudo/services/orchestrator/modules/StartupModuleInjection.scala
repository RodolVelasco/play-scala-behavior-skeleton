package com.applaudo.services.orchestrator.modules

import com.applaudo.services.actors.{ApplicationStarter, FileCreator}
import com.google.inject.AbstractModule
import play.api.{Configuration, Environment, Logger}

class StartupModuleInjection(/*environment: Environment, configuration: Configuration*/) extends AbstractModule {
  val logger: Logger = Logger(this.getClass)

  override def configure(): Unit = {
    logger.info("Initializing ...")
    //bind(classOf[FileCreator]).asEagerSingleton() Puede ir o no, ya que no cambio el log
    bind(classOf[ApplicationStarter]).asEagerSingleton()
  }
}