package com.applaudo.services.modules

import com.google.inject.AbstractModule
import play.api.{Configuration, Environment, Logger}

class StartupModule(environment: Environment, configuration: Configuration) extends AbstractModule {
  val logger: Logger = Logger(this.getClass)

  override def configure(): Unit = {
    logger.info("Initializing ...")
    //Comentando por pruebas de otra estructura de proyecto
    //bind(classOf[CounterStartup]).asEagerSingleton()
    //bind(classOf[FileMoverStartup]).asEagerSingleton()
  }
}
