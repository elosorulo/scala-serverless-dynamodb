package inputform.backend.utils

import spray.json._
import ItemJsonProtocol._
import inputform.backend.model.Item

object JsonUtils {

  def itemToJsonString(value: Item): String = {
    value.toJson.toString
  }

  def itemsListToJsonString(values: List[Item]): String = {
    values.toJson.toString
  }
}