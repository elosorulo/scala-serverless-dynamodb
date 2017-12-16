package inputform.backend.service

import inputform.backend.api.aws.ApiGatewayResponse

object ExecutionStatusResolver {
  def resolveStatus(body: => ApiGatewayResponse): ApiGatewayResponse = {
    body
  }
}