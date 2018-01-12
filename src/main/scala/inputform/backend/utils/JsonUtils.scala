package inputform.backend.utils

import inputform.backend.model.{ErrorResponse, Item}
import inputform.backend.utils.CustomJsonProtocol._
import spray.json._

object JsonUtils {

  def itemToJsonString(value: Item): String = {
    value.toJson.toString
  }

  def itemsListToJsonString(values: List[Item]): String = {
    values.toJson.toString
  }

  def jsonStringToItem(value: String): Item = {
    value.parseJson.convertTo[Item]
  }

  def jsonStringToItemsList(value: String): List[Item] = {
    value.parseJson.convertTo[List[Item]]
  }

  def errorResponseToJsonString(errorResponse: ErrorResponse): String ={
    errorResponse.toJson.toString
  }
}