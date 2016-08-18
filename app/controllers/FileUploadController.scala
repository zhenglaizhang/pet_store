package controllers

import java.io.File

import play.api.mvc.{Action, Controller}

class FileUploadController extends Controller {


  def createProfile = Action(parse.multipartFormData) { request =>
    val formData = request.body.asFormUrlEncoded
    val email: String = formData("email").head
    val name: String = formData("name").head
    val userId: Long = 120L // save User and the id returned
    request.body.file("displayPic").map { picture =>
      val path = "/tmp/user"
      if (!picture.filename.isEmpty) {
        picture.ref.moveTo(new File(path + userId + ".jpeg"))
      }
      Ok("Successfully added user")
    }.getOrElse {
      BadRequest("Failed to add user")
    }
  }

}
