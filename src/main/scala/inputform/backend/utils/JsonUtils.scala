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

  def jsonStringToItem(value: String): Item = value.parseJson.convertTo[Item]

  def jsonStringToItemsList(value: String): List[Item] = value.parseJson.convertTo[List[Item]]
}