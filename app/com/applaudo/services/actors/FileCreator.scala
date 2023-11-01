package com.applaudo.services.actors

import akka.actor.ActorSystem
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import com.applaudo.services.models.FileType
import com.applaudo.services.orchestrator.flows.AppFlows
import com.applaudo.services.shared.AppFunctions.fileTypeInitialDelayMap
import play.api.Logger

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext
import scala.concurrent.duration.DurationInt

object FileCreator {
  sealed trait Event
  final case class CreateFile(fileType: FileType) extends Event

  val logger: Logger = Logger(this.getClass)
  logger.info("Initializing Object FileCreator ...")
}

@Singleton
class FileCreator @Inject()()(implicit ec: ExecutionContext, system: ActorSystem, appFlows: AppFlows) {

  import FileCreator._
  def apply(fileType: FileType): Behavior[Event] = {
    logger.info("apply ...")
    idle(fileType)
  }

  def idle(fileType: FileType): Behavior[Event] = Behaviors.withTimers[Event] {
    logger.info("idle ...")
    timers =>
      logger.info(s"Starting $fileType Actor")
      timers.startTimerWithFixedDelay(CreateFile(fileType), fileTypeInitialDelayMap(fileType), 15.seconds)
      Behaviors.receiveMessagePartial {
        case CreateFile(fileType) =>
          logger.info(s"Got event to create file $fileType")
          val startTime = System.currentTimeMillis()
          appFlows.methodoDeChequeo(fileType).map { x =>
            val endTime = System.currentTimeMillis
            logger.info(s"Data => $x")
          }.recover {
            case e: Exception =>
              logger.error(s"AppFlows.createFile error: ${e.printStackTrace()}", e)
          }
          Behaviors.same

        case x: Any => logger.warn(s"Unhandled request: $x in FileCreator actor"); Behaviors.same

      }
  }
}
