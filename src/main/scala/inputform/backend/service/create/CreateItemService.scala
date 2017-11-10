package inputform.backend.service.create

import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}
import inputform.backend.api.ApiGatewayResponse

import scala.collection.JavaConverters

class CreateItemService extends RequestHandler[java.util.Map[String, Object], ApiGatewayResponse] {

  def handleRequest(request: java.util.Map[String, Object], context: Context): ApiGatewayResponse = {
    ApiGatewayResponse(statusCode = 200, body = "", headers = JavaConverters.mapAsJavaMap[String, Object](Map.empty), false)
  }
}
