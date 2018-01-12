package inputform.backend.service

import inputform.backend.api.aws.ApiGatewayResponse
import inputform.backend.model.{ErrorResponse, ErrorTypes, InputFormException}
import inputform.backend.utils.{JsonUtils, Logger}

import scala.collection.JavaConverters

object ExecutionStatusResolver extends ErrorTypes {
  def resolveStatus(body: => ApiGatewayResponse): ApiGatewayResponse = {
    try {
      body
    } catch {
      case e: InputFormException => checkStatus(e)
      case e: Exception =>
        Logger.error(e.getMessage)
        ApiGatewayResponse(
        statusCode = 503,
        body = JsonUtils.errorResponseToJsonString(errorResponse = ErrorResponse(
            errorCode = 1000,
            message = "An unknown error has occured.",
            errorType = UNKNOWN_ERROR)),
        headers = JavaConverters.mapAsJavaMap[String, Object](Map.empty),
        base64Encoded = false)
    }
  }

  private def checkStatus(exception: InputFormException): ApiGatewayResponse= {
    val errorType: String = exception.errorCode match {
      //Invalid body
      case 1001 => INVALID_INPUT_ERROR
      //Invalid path Parameters
      case 1002 => INVALID_INPUT_ERROR
      //DynamoDB Connection error
      case 1003 => INTERNAL_ERROR
      //DynamoDB Element not found
      case 1004 => BUSINESS_ERROR
      //Serialization error
      case 1005 => BUSINESS_ERROR
      case _ => {
        INTERNAL_ERROR
      }
    }
    ApiGatewayResponse(
      statusCode = 503,
      body = JsonUtils.errorResponseToJsonString(errorResponse = ErrorResponse(
        errorCode = exception.errorCode,
        message = exception.message,
        errorType = errorType)),
      headers = JavaConverters.mapAsJavaMap[String, Object](Map.empty),
      base64Encoded = false)
  }
}