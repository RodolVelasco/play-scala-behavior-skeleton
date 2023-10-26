package com.applaudo.services.controllers

import play.api.Logger
import play.api.mvc._

import javax.inject._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  val logger: Logger = Logger(this.getClass)
  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    logger.info("Entrando a index")
    Ok(com.applaudo.services.views.html.index())
  }

  def explore() = Action { implicit request: Request[AnyContent] =>
    logger.info("Entrando a explore")
    Ok(com.applaudo.services.views.html.explore())
  }

  def tutorial() = Action { implicit request: Request[AnyContent] =>
    logger.info("Entrando a tutorial")
    Ok(com.applaudo.services.views.html.tutorial())
  }
  
}
