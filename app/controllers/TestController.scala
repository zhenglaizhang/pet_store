package controllers

import java.io.File

import akka.util.ByteString
import play.api.http.HttpEntity
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller, ResponseHeader, Result}

import scala.xml.Node

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

  def getConfig = Action {
    implicit request =>
      val xmlResponse: Node = <metadata>
        <company>TinySensors</company>
        <batch>md2907</batch>
      </metadata>
      val jsonResponse = Json.obj("metadata" -> Json.arr(
                                                          Json.obj("company" -> "TinySensors"),
                                                          Json.obj("batch" -> "md2907"))
                                 )
      render {
        /*
        Play's helper methods that check to see if the request accepts the response of the application/xml and application/json types, respectively
         */
        case Accepts.Xml() => Ok(xmlResponse)
          /*
          if the same response response is sent for application/json and text/javascript
           */
          // | => & ? TODO bug fix
        case Accepts.Json() | Accepts.JavaScript() => Ok(jsonResponse)
        //        case Accepts.Html() => _
        //        case Accepts.JavaScript() =>
      }
  }
}
