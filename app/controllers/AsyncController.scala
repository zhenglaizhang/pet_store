package controllers

import java.io.File
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.inject._

import akka.actor.ActorSystem
import play.api.mvc._

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future, Promise}

/**
  * This controller creates an `Action` that demonstrates how to write
  * simple asynchronous code in a controller. It uses a timer to
  * asynchronously delay sending a response for 1 second.
  *
  * @param actorSystem We need the `ActorSystem`'s `Scheduler` to
  *                    run code after a delay.
  * @param exec        We need an `ExecutionContext` to execute our
  *                    asynchronous code.
  */
@Singleton
class AsyncController @Inject()(actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends Controller {

  /**
    * Create an Action that returns a plain text message after a delay
    * of 1 second.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/message`.
    */
  def message = Action.async {
    getFutureMessage(1.second).map { msg => Ok(msg) }
  }

  private def getFutureMessage(delayTime: FiniteDuration): Future[String] = {
    val promise: Promise[String] = Promise[String]()
    actorSystem.scheduler.scheduleOnce(delayTime) {
      promise.success("Hi!")
    }
    promise.future
  }


  def getReport(fileName: String) = Action.async {
    Future {
      val file = new File(fileName)
      if (file.exists()) {
        val info = file.lastModified()
        Ok(s"${file.getAbsoluteFile} lastModified on ${new Date(info)}")
      } else {
        // 204
        NoContent
      }
    }
  }

  def getReport2(fileName: String) = Action.async {
    val mayBeFile = Future {
      new File(fileName)
    }

    val timeout = play.api.libs.concurrent.Promise.timeout("Past max time", 10, TimeUnit.SECONDS)

    Future.firstCompletedOf(Seq(mayBeFile, timeout)).map {
      case f: File =>
        if (f.exists()) {
          val info = f.lastModified()
          Ok(s"${f.getAbsoluteFile} lastModified on ${new Date(info)}")
        } else {
          NoContent
        }
      case t: String => InternalServerError(t)
    }
  }

}
