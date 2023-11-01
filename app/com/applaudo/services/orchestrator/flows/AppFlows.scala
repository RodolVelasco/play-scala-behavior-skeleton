package com.applaudo.services.orchestrator.flows

import akka.actor.ActorSystem
import akka.stream.Materializer
import com.applaudo.services.models.FileType
import play.api.Logger

import java.util.concurrent.atomic.AtomicInteger
import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
final class AppFlows @Inject()(/*cosmosDb: CosmosDb */)
                              (implicit materializer: Materializer,
                               ec: ExecutionContext, ac: ActorSystem) {
  val logger: Logger = Logger(this.getClass)

  val firstFileCount = new AtomicInteger()
  val secondFileCount = new AtomicInteger()
  val thirdFileCount = new AtomicInteger()

  def getCounter(fileType: FileType): AtomicInteger =
    fileType match {
      case FileType.AlgunArchivo => firstFileCount
      case FileType.AlgunOtroArchivo => secondFileCount
      case FileType.AlgunOtroArchivoDiferente => thirdFileCount
    }

  def methodoDeChequeo(algunArchivo: FileType) = {
    algunArchivo match {
      case FileType.AlgunArchivo => Future.successful(List("Archivo1"))
      case FileType.AlgunOtroArchivo => Future.successful(List("Archivo1","Archivo2"))
      case FileType.AlgunOtroArchivoDiferente => Future.successful(List("Archivo1","Archivo2","Archivo3"))
    }
  }
}
