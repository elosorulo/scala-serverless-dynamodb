package inputform.backend.utils

import spray.json._
import CustomJsonProtocol._
import inputform.backend.model.{ErrorResponse, Input, Item}

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

  def getIdFromInput(pathParameters: Object): String  = {
    JsonParser.apply(pathParameters.toString).convertTo[Input].id
  }
}