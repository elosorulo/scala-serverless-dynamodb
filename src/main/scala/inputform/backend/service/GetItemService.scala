package inputform.backend.service

import java.util
import javax.inject.Inject

import com.amazonaws.services.lambda.runtime.Context
import inputform.backend.api.aws.ApiGatewayResponse
import inputform.backend.model.ItemDao
import inputform.backend.utils.JsonUtils

import scala.collection.JavaConverters
import scala.collection.JavaConverters._

class GetItemService @Inject() (itemDao: ItemDao) extends Service {
  override def execute(request: util.Map[String, Object], context: Context): ApiGatewayResponse = {
    getId(request) match {
      case Some(id) =>
        val body: String = JsonUtils.itemToJsonString(itemDao.get(id.toString))
        ApiGatewayResponse(statusCode = 200, body = body,
          headers = JavaConverters.mapAsJavaMap[String, Object](Map.empty), false)

      case None => ApiGatewayResponse(statusCode = 400, body = "",
        headers = JavaConverters.mapAsJavaMap[String, Object](Map.empty), false)
    }
  }

  def getId(request: java.util.Map[String, Object]): Option[Object] = {
    request.asScala.get("id")
  }
}
