package controllers

import java.io.File

import akka.util.ByteString
import play.api.http.HttpEntity
import play.api.mvc.{Action, Controller, ResponseHeader, Result}

class TestController extends Controller {

  /*
A result can send JSON, XML, and images as a response, apart from a String content.
 */

  // In Play, the response to a request is a result.
  /*
  In Play, the response to a request is a result. A result has two components: the response header and the response body

  a result has additional functions that equips us with better means to handle response headers, sessions, cookies
   */

  val todo = Action {
    NotImplemented[play.twirl.api.Html](views.html.defaultpages.todo())
  }

  def plainResult = Action {
    Result(header = ResponseHeader(200, Map(CONTENT_TYPE -> "text/plain")),
            body = HttpEntity.Strict(ByteString.fromString("hello world"), contentType = Option("text/plain")))

  }

  def getUserImage(userId: Long) = Action {
    val path = s"/tmp/user/$userId.png"
    val img = new File(path)
    if (img.exists()) {
      /*
If it is inline, the browser should attempt to render it within the browser window. If it cannot, it will resort to an external program, prompting the user.
With attachment, it will immediately go to the user, and not try to load it in the browser, whether it can or not.
       */
      Ok.sendFile(content = img, inline = true)
    } else {
      NoContent
    }
  }

  def echo = Action(parse.text) {
    request =>
      Status(200)("Got: " + request.body)
  }
}
