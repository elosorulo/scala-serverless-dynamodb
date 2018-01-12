package inputform.backend.service

import java.util
import javax.inject.Inject

import com.amazonaws.services.lambda.runtime.Context
import inputform.backend.api.aws.ApiGatewayResponse
import inputform.backend.model.{InputFormException, ItemDao}
import inputform.backend.utils.{JsonUtils, Logger}

import scala.collection.JavaConverters
import scala.collection.JavaConverters._

class GetItemService @Inject() (itemDao: ItemDao) extends Service {
  override def execute(request: util.Map[String, Object], context: Context): ApiGatewayResponse = {
    val id = getId(request)
    Logger.info(s"Getting item from DAO.")
    val body: String = JsonUtils.itemToJsonString(itemDao.get(id))
    Logger.info(s"Returning API Gateway Response.")
    ApiGatewayResponse(statusCode = 200, body = body,
    headers = JavaConverters.mapAsJavaMap[String, Object](Map.empty), false)
  }

  def getId(request: java.util.Map[String, Object]): String = {
    request.asScala.get("pathParameters") match {
      case None => throw InputFormException(1002, "Invalid Path Parameters")
      case Some(pp) =>
      pp.asInstanceOf[java.util.LinkedHashMap[String, Object]]
      .asScala.get("id") match {
        case None => throw InputFormException(1002, "Invalid Path Parameters")
        case Some(id) => id.toString
      }
    }
  }
}
