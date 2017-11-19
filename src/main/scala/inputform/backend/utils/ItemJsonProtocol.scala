package inputform.backend.utils

import inputform.backend.model.Item
import spray.json.DefaultJsonProtocol


object ItemJsonProtocol extends DefaultJsonProtocol {
  implicit val itemFormat = jsonFormat9(Item)
}