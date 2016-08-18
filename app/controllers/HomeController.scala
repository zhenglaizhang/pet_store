package controllers

import javax.inject._

import play.api.mvc._
import play.twirl.api.Html

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject() extends Controller {

  /**
    * Create an Action to render an HTML page with a welcome message.
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */

  // This defines an action method that generates an HTTP OK response with text content
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def login(name: String, password: String) = Action {
    ???
  }

  def index2 = Action {
    val content = Html("<div>This is the content for the sample app</div>")
    Ok(views.html.main("Home")(content))
  }

  def hello(name: String) = Action {
    //    Ok(s"Hello $name")
    Ok(views.html.hello(name))
  }
}
