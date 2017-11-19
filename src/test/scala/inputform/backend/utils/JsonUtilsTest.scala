package inputform.backend.utils

import inputform.backend.model.Item
import org.apache.logging.log4j.scala.Logging
import org.joda.time.DateTime
import org.scalatest.{FunSuite, Matchers}

class JsonUtilsTest extends FunSuite with Matchers with Logging {

  private final val ITEM: Item = Item("12345", "John", "Doe", "johndoe@mail.com", "abc", "", "", DateTime.now.toString,
    DateTime.now.toString)

  test("Testing correct Serialization of object to Json.") {
    val output = JsonUtils.itemToJsonString(ITEM)
    println(output)
    logger.info(output)
  }
}

case class FooBar(name: String, fooBarId: String)