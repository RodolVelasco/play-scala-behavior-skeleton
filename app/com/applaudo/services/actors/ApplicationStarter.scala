package com.applaudo.services.actors

import akka.actor.typed.scaladsl.adapter.ClassicActorSystemOps
import akka.stream.Materializer
import com.applaudo.services.models.FileType
import com.applaudo.services.orchestrator.flows.AppFlows
import play.api.Logger

import javax.inject.{ Inject, Singleton }
import scala.concurrent.ExecutionContext

@Singleton
final class ApplicationStarter @Inject()(appFlows: AppFlows, fileCreator: FileCreator)
                                        (implicit val materializer: Materializer, implicit val ec: ExecutionContext) {
  val logger: Logger = Logger(this.getClass)

  logger.info("Initializing ...")
  appFlows.methodoDeChequeo(FileType.AlgunArchivo).map(_ => logger.info(s"Initial checks done for ${FileType.AlgunArchivo.value()}"))
  appFlows.methodoDeChequeo(FileType.AlgunOtroArchivo).map(_ => logger.info(s"Initial checks done for ${FileType.AlgunOtroArchivo.value()}"))
  appFlows.methodoDeChequeo(FileType.AlgunOtroArchivoDiferente).map(_ => logger.info(s"Initial checks done for ${FileType.AlgunOtroArchivoDiferente.value()}"))

  val algunArchivoActor: Any = materializer.system.spawn(fileCreator(FileType.AlgunArchivo), "AlgunArchivoActor")
  val algunOtroArchivoActor: Any = materializer.system.spawn(fileCreator(FileType.AlgunOtroArchivo), "AlgunOtroArchivoActor")
  val algunOtroArchivoDiferenteActor: Any = materializer.system.spawn(fileCreator(FileType.AlgunOtroArchivoDiferente), "AlgunOtroArchivoDiferenteActor")
}
