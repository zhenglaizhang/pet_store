package controllers

import models.Task
import play.api.mvc.{Action, Controller}

class TaskController extends Controller {

  def index = Action {
    Redirect(routes.TaskController.tasks())
  }

  def tasks = Action {
    Ok(views.html.task("Task Tracker")(Task.all))
  }

  def newTask = Action(parse.urlFormEncoded) {
    implicit request =>
      Task.add(request.body("taskName").head)

      Redirect(routes.TaskController.index())
  }

  def deleteTask(id: Int) = Action {
    Task.delete(id)
    Ok
  }
}
