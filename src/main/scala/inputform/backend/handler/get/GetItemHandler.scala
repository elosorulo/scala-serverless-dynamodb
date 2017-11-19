package inputform.backend.handler.get

import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}
import inputform.backend.api.aws.ApiGatewayResponse
import inputform.backend.config.{GetItemModule, Initializer}
import inputform.backend.service.Service
import org.apache.logging.log4j.scala.Logging

class GetItemHandler extends RequestHandler[java.util.Map[String, Object], ApiGatewayResponse] with Initializer with Logging {

  def handleRequest(request: java.util.Map[String, Object], context: Context): ApiGatewayResponse = {
    logger.info("Handling GetItems Lambda Function.")
    logger.info("Initializing GetItemsService Dependencies.")
    val service: Service = initialize(new GetItemModule)
    logger.info("Executing getItemsService.")
    service.execute(request, context)
  }
}
