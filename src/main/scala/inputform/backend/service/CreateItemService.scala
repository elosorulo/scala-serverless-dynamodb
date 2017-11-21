package inputform.backend.service

import javax.inject.Inject

import com.amazonaws.services.lambda.runtime.Context
import inputform.backend.api.aws.ApiGatewayResponse
import inputform.backend.model.ItemDao
import inputform.backend.utils.JsonUtils

import scala.collection.JavaConverters
import scala.collection.JavaConverters._

class CreateItemService @Inject() (itemDao: ItemDao) extends Service {
  override def execute(request: java.util.Map[String, Object], context: Context): ApiGatewayResponse = {
    getBody(request) match {
      case Some(body) =>
        itemDao.put(JsonUtils.jsonStringToItem(body.toString))
        ApiGatewayResponse(statusCode = 200, body = "",
          headers = JavaConverters.mapAsJavaMap[String, Object](Map.empty), false)

      case None => ApiGatewayResponse(statusCode = 400, body = "",
        headers = JavaConverters.mapAsJavaMap[String, Object](Map.empty), false)
    }

  }

  def getBody(request: java.util.Map[String, Object]): Option[Object] = {
    request.asScala.get("body")
  }
}
