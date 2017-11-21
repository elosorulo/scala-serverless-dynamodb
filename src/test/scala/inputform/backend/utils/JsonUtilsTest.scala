package inputform.backend.utils

import inputform.backend.model.Item
import org.apache.logging.log4j.scala.Logging
import org.joda.time.DateTime
import org.scalatest.{FunSuite, Matchers}

class JsonUtilsTest extends FunSuite with Matchers with Logging {

  private final val ITEM_A: Item = Item("12345", "John", "Doe", "johndoe@mail.com", "abc",
    "Some Options", "Yes.", DateTime.now.toString, DateTime.now.toString)

  private final val ITEM_B: Item = Item("12346", "Jane", "Doe", "janedoe@mail.com", "def",
    "Some Options", "No.", DateTime.now.toString, DateTime.now.toString)

  test("Single Item Marshalling and Unmarshalling Test.") {
    val itemAJsonString: String = JsonUtils.itemToJsonString(ITEM_A)

    val stringToItemA: Item = JsonUtils.jsonStringToItem(itemAJsonString)

    ITEM_A shouldEqual stringToItemA
  }

  test("Items List Marshalling and Unmarshalling Test.") {
    val itemsJsonString: String = JsonUtils.itemsListToJsonString(List(ITEM_A, ITEM_B))

    val stringToItems: List[Item] = JsonUtils.jsonStringToItemsList(itemsJsonString)

    List(ITEM_A, ITEM_B) shouldEqual stringToItems

  }
}