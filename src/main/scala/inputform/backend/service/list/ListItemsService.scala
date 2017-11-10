package inputform.backend.service.list

import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}
import inputform.backend.api.ApiGatewayResponse
import inputform.backend.model.dao.{ItemDao, ItemDaoImpl}
import inputform.backend.utils.JsonUtils

import scala.collection.JavaConverters

class ListItemsService extends RequestHandler[java.util.Map[String, Object], ApiGatewayResponse] {
  def handleRequest(request: java.util.Map[String, Object], context: Context): ApiGatewayResponse = {
    val itemDao: ItemDao = new ItemDaoImpl
    val body: String = JsonUtils.toJson(itemDao.getAll())
    ApiGatewayResponse(statusCode = 200, body = body,
      headers = JavaConverters.mapAsJavaMap[String, Object](Map.empty), false)
  }
}
