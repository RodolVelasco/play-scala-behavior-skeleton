package com.applaudo.services.poc

import play.api.Logger

object PlayApiLogger extends App {
  val logger: Logger = Logger(this.getClass)
  miMetodo()
  def miMetodo() = {
    logger.info("Mensaje informativo")
    logger.debug("Mensaje de depuración")
    logger.warn("Mensaje de advertencia")
    logger.error("Mensaje de error")
  }
}