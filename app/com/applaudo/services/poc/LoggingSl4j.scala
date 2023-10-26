package com.applaudo.services.poc

import org.slf4j.LoggerFactory

object LoggingSl4j extends App {
  private val logger = LoggerFactory.getLogger(getClass.getSimpleName)

  logger.info("Inicio de la aplicación")
  logger.debug("Mensaje de Debug")
  logger.warn("Mensaje de Advertencia")
  logger.error("Mensaje de Error")
}
