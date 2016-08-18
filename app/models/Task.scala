package models

case class Task(id: Int, name: String)

object Task {
  private var taskList = List[Task]()
  private var id       = 0;

  def all = taskList

  def add(taskName: String) = {
    val newId: Int = id + 1
    taskList = taskList ++ List(Task(newId, taskName))
    id += 1
  }

  def delete(taskId: Int) = {
    taskList = taskList.filterNot(_.id == taskId)
  }
}

