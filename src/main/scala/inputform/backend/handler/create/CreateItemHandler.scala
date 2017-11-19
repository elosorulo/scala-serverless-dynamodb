package inputform.backend.handler.create

import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}
import inputform.backend.api.aws.ApiGatewayResponse
import inputform.backend.config.{CreateItemModule, Initializer}
import inputform.backend.service.Service

class CreateItemHandler extends RequestHandler[java.util.Map[String, Object], ApiGatewayResponse] with Initializer {

  def handleRequest(request: java.util.Map[String, Object], context: Context): ApiGatewayResponse = {
    val service: Service = initialize(new CreateItemModule)
    service.execute(request, context)
  }
}
