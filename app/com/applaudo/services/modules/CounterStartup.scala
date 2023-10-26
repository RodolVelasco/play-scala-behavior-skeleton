package com.applaudo.services.modules

import com.applaudo.services.actors.CounterActor

import javax.inject.{Inject, Singleton}
import akka.actor.{ActorSystem, Props}
import play.api.{Configuration, Logger}

import scala.concurrent.duration._
import play.api.inject.ApplicationLifecycle

@Singleton
class CounterStartup @Inject() (configuration: Configuration, actorSystem: ActorSystem, lifecycle: ApplicationLifecycle) {

  val logger: Logger = Logger(this.getClass)

  logger.info("Initializing ...")

  // Iniciar el actor
  val counterActor = actorSystem.actorOf(Props[CounterActor](), name = "counterActor")

  // Enviar un mensaje "tick" al actor cada segundo
  private val durationString = configuration.get[String]("app.counter.duration")
  private val duration: FiniteDuration = Duration(durationString).asInstanceOf[FiniteDuration]
  actorSystem.scheduler.scheduleAtFixedRate(0.seconds, duration)(() => counterActor ! "tick")(actorSystem.dispatcher)

  logger.info("Scheduling ...")

  // Registrar para detener el actor cuando la aplicación se detiene
  lifecycle.addStopHook { () =>
    actorSystem.terminate()
  }
}