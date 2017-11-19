package inputform.backend.service

import com.amazonaws.services.lambda.runtime.Context
import inputform.backend.api.aws.ApiGatewayResponse

trait Service {
  def execute(input: java.util.Map[String, Object], context: Context): ApiGatewayResponse
}
