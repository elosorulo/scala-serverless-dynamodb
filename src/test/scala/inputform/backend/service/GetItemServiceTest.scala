package inputform.backend.handler

import com.amazonaws.services.lambda.runtime.{Context, LambdaLogger}
import inputform.backend.api.aws.ApiGatewayResponse
import inputform.backend.model.{Item, ItemDao}
import inputform.backend.service.GetItemService
import inputform.backend.utils.JsonUtils
import org.joda.time.DateTime
import org.mockito.Mockito
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{FunSuite, Matchers}

import scala.collection.JavaConverters

class GetItemServiceTest extends FunSuite with Matchers with MockitoSugar {

  private final val ITEM: Item = Item("12345", "John", "Doe", "johndoe@mail.com", "abc", "", false, DateTime.now.toString,
    DateTime.now.toString)

  test("StatusCode: 200 - OK") {
    val service: GetItemService = new GetItemService(mockDao())
    val response: ApiGatewayResponse = service.execute(mockInput(), mockContext())
    response.statusCode shouldBe 200
    response.headers shouldBe JavaConverters.mapAsJavaMap(Map.empty)

    val stringToItemA: Item = JsonUtils.jsonStringToItem(response.body)
    ITEM shouldEqual stringToItemA
  }

  private def mockDao(): ItemDao = {
    val itemDao: ItemDao = mock[ItemDao]
    Mockito.when(itemDao.get("12345")).thenReturn(ITEM)
    itemDao
  }

  private def mockContext(): Context = {
    val context = mock[Context]
    val logger = mock[LambdaLogger]
    Mockito.doNothing().when(logger).log(org.mockito.ArgumentMatchers.any[String])
    Mockito.when(context.getLogger).thenReturn(logger)
    Mockito.when(context.getRemainingTimeInMillis).thenReturn(1000)
    context
  }

  private def mockInput(): java.util.Map[String, Object] = {
    JavaConverters.mapAsJavaMap(Map("id" -> "12345"))
  }

}
