package inputform.backend.service

import java.util
import javax.inject.Inject

import com.amazonaws.services.lambda.runtime.Context
import inputform.backend.api.aws.ApiGatewayResponse
import inputform.backend.model.ItemDao
import inputform.backend.utils.{JsonUtils, Logger}

import scala.collection.JavaConverters
import scala.collection.JavaConverters._

class GetItemService @Inject() (itemDao: ItemDao) extends Service {
  override def execute(request: util.Map[String, Object], context: Context): ApiGatewayResponse = {
    getId(request) match {
      case Some(id) =>
        Logger.info(s"Getting item from DAO.")
        val body: String = JsonUtils.itemToJsonString(itemDao.get(id))
        Logger.info(s"Returning API Gateway Response.")
        ApiGatewayResponse(statusCode = 200, body = body,
          headers = JavaConverters.mapAsJavaMap[String, Object](Map.empty), false)

      case None =>
        Logger.error(s"No input found.")
        ApiGatewayResponse(statusCode = 400, body = "",
        headers = JavaConverters.mapAsJavaMap[String, Object](Map.empty), false)
    }
  }

  def getId(request: java.util.Map[String, Object]): Option[String] = {
    request.asScala.get("pathParameters") match {
      case None => None
      case Some(pp) => Some((JsonUtils.getIdFromInput(pp)))
    }
  }
}
