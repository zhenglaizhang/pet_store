package controllers

import models._
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{Action, AnyContent, Controller}
import scala.concurrent.ExecutionContext.Implicits.global

class SubscribeController extends Controller {

  // a parser that transforms the request body into a subscription object.
  val parseAsSubscription = parse.using { request =>
    parse.json.map {
      body =>
        val emailId = (body \ "emailId").as[String]
        val interval = (body \ "interval").as[String]
        Subscription(emailId, interval)
    }
  }

  implicit val subWrites = Json.writes[Subscription]

  def getSubByParser = Action(parseAsSubscription) {
    request =>
      val subscription: Subscription = request.body
      Ok(Json.toJson(subscription))
  }

  // tolerant parsers
  // it ignores the content type header in the request and parses based on the type specified.
  // Parse the body as Json without checking the Content-Type.
  def subscribe22 = Action(parse.tolerantJson) {
    request =>
      val reqData: JsValue = request.body
      val emailId = (reqData \ "email").as[String]
      val interval = (reqData \ "interval").as[String]
      Ok(s"added $emailId to subscriber's list and will send updates every $interval")
  }

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
