package inputform.backend.service

import javax.inject.Inject

import com.amazonaws.services.lambda.runtime.Context
import inputform.backend.api.aws.ApiGatewayResponse
import inputform.backend.model.ItemDao
import inputform.backend.utils.{JsonUtils, Logger}

import scala.collection.JavaConverters
import scala.collection.JavaConverters._

class CreateItemService @Inject() (itemDao: ItemDao) extends Service {
  override def execute(request: java.util.Map[String, Object], context: Context): ApiGatewayResponse = {
    getBody(request) match {
      case Some(body) =>
        Logger.info(s"Putting item in DAO.")
        itemDao.put(JsonUtils.jsonStringToItem(body.toString))
        Logger.info(s"Returning API Gateway Response.")
        ApiGatewayResponse(statusCode = 200, body = "",
          headers = JavaConverters.mapAsJavaMap[String, Object](Map.empty), false)

      case None =>
        Logger.error(s"No input found.")
        ApiGatewayResponse(statusCode = 400, body = "",
        headers = JavaConverters.mapAsJavaMap[String, Object](Map.empty), false)
    }

  }

  def getBody(request: java.util.Map[String, Object]): Option[Object] = {
    request.asScala.get("body")
  }
}
