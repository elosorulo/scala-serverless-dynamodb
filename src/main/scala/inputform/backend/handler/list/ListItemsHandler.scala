package inputform.backend.handler.list

import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}
import inputform.backend.api.aws.ApiGatewayResponse
import inputform.backend.config.{Initializer, ListItemsModule}
import inputform.backend.service.Service

class ListItemsHandler extends RequestHandler[java.util.Map[String, Object], ApiGatewayResponse] with Initializer {
  def handleRequest(request: java.util.Map[String, Object], context: Context): ApiGatewayResponse = {
    val service: Service = initialize(new ListItemsModule)
    service.execute(request, context)
  }
}