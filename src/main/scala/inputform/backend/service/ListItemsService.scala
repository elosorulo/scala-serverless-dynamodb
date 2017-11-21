package inputform.backend.service

import java.util
import javax.inject.{Inject, Singleton}

import com.amazonaws.services.lambda.runtime.Context
import inputform.backend.api.aws.ApiGatewayResponse
import inputform.backend.model.ItemDao
import inputform.backend.utils.JsonUtils

import scala.collection.JavaConverters

@Singleton
class ListItemsService @Inject() (itemDao: ItemDao)
  extends Service {
  override def execute(request: util.Map[String, Object], context: Context): ApiGatewayResponse = {
    val body: String = JsonUtils.itemsListToJsonString(itemDao.getAll())
    ApiGatewayResponse(statusCode = 200, body = body,
      headers = JavaConverters.mapAsJavaMap[String, Object](Map.empty), false)
  }
}
