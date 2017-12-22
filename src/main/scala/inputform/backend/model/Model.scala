package inputform.backend.model

case class Item(id: String, firstName: String, lastName: String, email: String, comments: String, options: String,
               checked: Boolean, createdAt: String, updatedAt: String)

trait ErrorTypes {
  protected final val UNKNOWN_ERROR = "UNKNOWN_ERROR"
  protected final val BUSINESS_ERROR = "BUSINESS_ERROR"
  protected final val INTERNAL_ERROR = "INTERNAL_ERROR"
  protected final val INVALID_INPUT_ERROR = "INVALID_INPUT_ERROR"
}

case class ErrorResponse(errorCode: Int, message: String, errorType: String)

case class InputFormException(errorCode: Int, message: String = "",
                              cause: Throwable = None.orNull) extends Exception(message, cause)

case class Input(id: String)