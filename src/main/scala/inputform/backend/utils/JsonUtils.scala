package inputform.backend.utils

import java.io.StringWriter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper

object JsonUtils {

  val mapper = new ObjectMapper() with ScalaObjectMapper

  def toJson(value: Any): String = {
    mapper.writeValue(new StringWriter, value).toString
  }
}
