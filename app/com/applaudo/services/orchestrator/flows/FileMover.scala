package com.applaudo.services.orchestrator.flows

import java.nio.file._
import akka.actor.{Actor, ActorLogging}
import play.api.{Configuration, Logger}

import javax.inject.Inject
import scala.jdk.CollectionConverters._

class FileMover @Inject()(configuration: Configuration) extends Actor with ActorLogging {

  val logger: Logger = Logger(this.getClass)

  logger.info("Initializing ...")

  private val rootFolder = Paths.get(System.getProperty("user.dir"))
  private val sourceFolder = rootFolder.resolve(configuration.get[String]("app.folder.source"))
  private val destinationFolder = rootFolder.resolve(configuration.get[String]("app.folder.destination"))

  logger.info(s"Source: $sourceFolder")
  logger.info(s"Destination: $destinationFolder")

  override def preStart(): Unit = {
    logger.info("PreStart initializing ...")

    // Moving preexisting files in source folder
    Files.list(sourceFolder).forEach(path => {
      logger.info("Moving preexisting files ...")
      val filename = path.getFileName.toString
      readFileLineByLine(filename)
      moveFile(filename)
    })

    val watchService = FileSystems.getDefault.newWatchService()
    sourceFolder.register(watchService, StandardWatchEventKinds.ENTRY_CREATE)

    // Start monitoring on a new thread
    new Thread(() => {
      logger.info("Thread starts monitoring folder for changes")
      while (true) {
        logger.info("Source folder scanning ...")
        val key = watchService.take()
        key.pollEvents().forEach(event => {
          logger.info("Polling events ...")
          // Here, move the file from source to destination
          val filename = event.context().toString
          logger.info(s"Processing filename: $filename")
          readFileLineByLine(filename)
          moveFile(filename)
        })
        key.reset()
      }
    }).start()
  }

  override def receive: Receive = {
    case _ => // Here you can handle messages if necessary
  }

  private def moveFile(filename: String): Unit = {
    Files.move(sourceFolder.resolve(filename), destinationFolder.resolve(filename))
    logger.info(s"Filename: $filename was moved to destination folder")
  }

  private def readFileLineByLine(filename: String): Unit = {
    logger.info(s"Filename: $filename content is")
    val filePath = sourceFolder.resolve(filename)

    // Leer el archivo línea por línea y convertirlo a una secuencia de Scala
    val lines = Files.readAllLines(filePath).asScala

    // Procesar cada línea
    lines.foreach { line =>
      logger.info(line)
      // Aquí puedes hacer lo que necesites con cada línea
    }
  }
}
