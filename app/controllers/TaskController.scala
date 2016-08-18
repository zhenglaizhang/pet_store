package controllers

import play.api.mvc.{Action, Controller}

class TaskController extends Controller {

  def index = Action {
    Ok(views.html.task())
  }
}
