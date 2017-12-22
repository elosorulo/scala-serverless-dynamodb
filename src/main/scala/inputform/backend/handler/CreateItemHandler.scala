package inputform.backend.handler

import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}
import inputform.backend.api.aws.ApiGatewayResponse
import inputform.backend.config.{CreateItemModule, Initializer}
import inputform.backend.service.{ExecutionStatusResolver, Service}
import inputform.backend.utils.Logger

class CreateItemHandler extends RequestHandler[java.util.Map[String, Object], ApiGatewayResponse] with Initializer {

  def handleRequest(request: java.util.Map[String, Object], context: Context): ApiGatewayResponse = {
    ExecutionStatusResolver.resolveStatus({
      Logger.context = Some(context)
      Logger.info("Handling GetItems Lambda Function")
      Logger.info("Initializing GetItemsService Dependencies.")
      val service: Service = initialize(new CreateItemModule)
      Logger.info("Executing getItemsService.")
      service.execute(request, context)
    })
  }
}
