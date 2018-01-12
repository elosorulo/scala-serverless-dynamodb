package inputform.backend.utils

import com.amazonaws.services.lambda.runtime.Context

object Logger {

  private final val INFO_PREFIX = "INFO - "
  private final val WARN_PREFIX = "WARN - "
  private final val ERROR_PREFIX = "ERROR - "
  private final val REMAINING_TIME_PREFIX = "remainingTime:"
  private final val REMAINING_TIME_POSTFIX = "ms-"

  var context: Option[Context] = None

  def info(output: String): Unit = {
    context match {
      case None =>
      case Some(ctx) => ctx.getLogger.log(s"$INFO_PREFIX$REMAINING_TIME_PREFIX" +
        s"${ctx.getRemainingTimeInMillis}$REMAINING_TIME_POSTFIX" +
    output +
    "\n")
    }
  }

  def warn(output: String): Unit = {
    context match {
      case None =>
      case Some(ctx) => ctx.getLogger.log(s"$WARN_PREFIX$REMAINING_TIME_PREFIX" +
        s"${ctx.getRemainingTimeInMillis}$REMAINING_TIME_POSTFIX" +
        output +
        "\\n")
    }
  }

  def error(output: String): Unit = {
    context match {
      case None =>
      case Some(ctx) => ctx.getLogger.log(s"$ERROR_PREFIX$REMAINING_TIME_PREFIX" +
        s"${ctx.getRemainingTimeInMillis}$REMAINING_TIME_POSTFIX" +
        output +
        "\\n")
    }
  }

}
