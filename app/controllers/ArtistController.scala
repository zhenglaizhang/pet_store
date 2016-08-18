package controllers

import models.Artist
import play.api.mvc.{Action, Controller}

class ArtistController extends Controller {

  // Action apply method takes in a request and returns a result.
  def listArtist = Action {
    Ok(views.html.artist(Artist.fetchAll))
  }

  def fetchArtistByName(name: String) = Action {
    Ok(views.html.artist(Artist.fetchByName(name)))
  }

  def search(name: String, country: String) = Action {
    val result = Artist.fetchByNameOrCountry(name, country)
    if (result.isEmpty) {
      NoContent
    } else {
      Ok(views.html.artist(result))
    }
  }
}
