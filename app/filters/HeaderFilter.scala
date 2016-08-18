package filters

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import play.api.http.HeaderNames._
import play.api.mvc.Result

class HeaderFilter {

  /*
  filter adds all the headers to a response, which are required to disable caching in browsers.
   */
//  val noCache = Filter {
//    (nextFilter, rh) =>
//      nextFilter(rh) map {
//        result: Result => addNoCacheHeaders(result)
//      }
//  }


  private def addNoCacheHeaders(result: Result): Result = {
    result.withHeaders(
                        PRAGMA -> "no-cache",
                        CACHE_CONTROL -> "no-cache, no-store, must-revalidate, max-age=0",
                        EXPIRES -> serverTime)
  }


  private def serverTime = {
    val now = new DateTime()
    val dateFormat = DateTimeFormat.forPattern("EEE, dd MMM yyyy HH:mm:ss z")
    dateFormat.print(now)
  }

}
