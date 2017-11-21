package inputform.backend.service

import java.util
import javax.inject.Inject

import com.amazonaws.services.lambda.runtime.Context
import inputform.backend.api.aws.ApiGatewayResponse
import inputform.backend.model.{ItemDao, ItemDaoImpl}
import inputform.backend.model.dao.ItemDaoImpl
import inputform.backend.utils.JsonUtils

import scala.collection.JavaConverters

class GetItemService @Inject() (itemDao: ItemDao) extends Service {
  override def execute(request: util.Map[String, Object], context: Context): ApiGatewayResponse = {
    val itemDao: ItemDao = new ItemDaoImpl()
    val body: String = JsonUtils.itemToJsonString(itemDao.get(""))
    ApiGatewayResponse(statusCode = 200, body = body,
      headers = JavaConverters.mapAsJavaMap[String, Object](Map.empty), false)
  }
}
