package inputform.backend.service

import java.util
import javax.inject.Inject

import com.amazonaws.services.lambda.runtime.Context
import inputform.backend.api.aws.ApiGatewayResponse
import inputform.backend.model.ItemDao

import scala.collection.JavaConverters

class CreateItemService @Inject() (itemDao: ItemDao) extends Service {
  override def execute(request: util.Map[String, Object], context: Context): ApiGatewayResponse = {
    ApiGatewayResponse(statusCode = 200, body = "", headers = JavaConverters.mapAsJavaMap[String, Object](Map.empty), false)
  }
}
