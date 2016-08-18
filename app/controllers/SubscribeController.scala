package controllers

import play.api.libs.json.JsValue
import play.api.mvc.{Action, AnyContent, Controller}

class SubscribeController extends Controller {

  def subscribeOld = Action {
    request =>
      val reqBody: AnyContent = request.body
      val textContent: Option[String] = reqBody.asText
      textContent map {
        emailId => Ok(s"added $emailId to subscribers list")
      } getOrElse {
        BadRequest("improper request body")
      }
  }

  def subscribeText = Action(parse.text) {
    request =>
      Ok(s"added ${request.body} to subscribers list")
  }

  def subscribeJson = Action(parse.json) {
    request =>
      val reqData: JsValue = request.body
      // The \ operator is used to access the value of a particular key.
      // a \\ operator, which can be for the value of a key, though it may not be a direct child of the current node.
      val emailId = (reqData \ "emailId").as[String]
      val interval = (reqData \ "interval").as[String]
      Ok(s"Added $emailId to subscribers list with update interval as $interval")
  }

}
