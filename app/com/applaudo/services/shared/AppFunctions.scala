package com.applaudo.services.shared

import com.applaudo.services.models.FileType
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.ScalaObjectMapper

import scala.concurrent.duration.{DurationInt, FiniteDuration}

object AppFunctions {

  val objectMapper = new ObjectMapper() with ScalaObjectMapper

  def toJson[T](obj: T): String = objectMapper.writeValueAsString(obj)

  lazy val fileTypeInitialDelayMap: Map[FileType, FiniteDuration] = Map (
    FileType.AlgunArchivo -> 1.seconds,
    FileType.AlgunOtroArchivo -> 5.seconds,
    FileType.AlgunOtroArchivoDiferente -> 10.seconds
  )

}
