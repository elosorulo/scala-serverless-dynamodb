package inputform.backend.service

import com.amazonaws.services.lambda.runtime.{Context, LambdaLogger}
import inputform.backend.api.aws.ApiGatewayResponse
import inputform.backend.model.{Item, ItemDao}
import inputform.backend.utils.JsonUtils
import org.joda.time.DateTime
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{FunSuite, Matchers}

import scala.collection.JavaConverters

class CreateItemServiceTest extends FunSuite with Matchers with MockitoSugar {

  private final val ITEM: Item = Item("12345", "John", "Doe", "johndoe@mail.com", "abc", "", false, DateTime.now.toString,
    DateTime.now.toString)

  test("StatusCode: 200 - OK") {
    val create: CreateItemService = new CreateItemService(mockDao())
    val response: ApiGatewayResponse = create.execute(mockInput(), mockContext())

    response.body shouldBe ""
    response.statusCode shouldBe 200
    response.headers shouldBe JavaConverters.mapAsJavaMap(Map.empty)
  }

  private def mockDao(): ItemDao = {
    val itemDao: ItemDao = mock[ItemDao]
    Mockito.doNothing().when(itemDao).put(any[Item])
    itemDao
  }

  private def mockContext(): Context = {
    val context = mock[Context]
    val logger = mock[LambdaLogger]
    Mockito.doNothing().when(logger).log(any[String])
    Mockito.when(context.getLogger).thenReturn(logger)
    Mockito.when(context.getRemainingTimeInMillis).thenReturn(1000)
    context
  }

  private def mockInput(): java.util.Map[String, Object] = {
    val jsonItem: String = JsonUtils.itemToJsonString(ITEM)
    println(jsonItem)
    JavaConverters.mapAsJavaMap(Map("body" -> jsonItem))
  }
}
