package inputform.backend.utils

import inputform.backend.model.{ErrorResponse, Item}
import spray.json.DefaultJsonProtocol


object CustomJsonProtocol extends DefaultJsonProtocol {
  implicit val itemFormat = jsonFormat9(Item)
  implicit val errorResponseFormat = jsonFormat3(ErrorResponse)
}