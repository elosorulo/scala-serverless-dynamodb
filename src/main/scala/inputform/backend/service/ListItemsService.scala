package inputform.backend.service

import java.util
import javax.inject.{Inject, Singleton}

import com.amazonaws.services.lambda.runtime.Context
import inputform.backend.api.aws.ApiGatewayResponse
import inputform.backend.model.ItemDao
import inputform.backend.utils.{JsonUtils, Logger}

import scala.collection.JavaConverters

@Singleton
class ListItemsService @Inject() (itemDao: ItemDao)
  extends Service {
  override def execute(request: util.Map[String, Object], context: Context): ApiGatewayResponse = {
    Logger.info(s"Listing items from DAO.")
    val body: String = JsonUtils.itemsListToJsonString(itemDao.getAll())
      Logger.info(s"Returning API Gateway Response.")
    ApiGatewayResponse(statusCode = 200, body = body,
      headers = JavaConverters.mapAsJavaMap[String, Object](Map.empty), false)
  }
}
