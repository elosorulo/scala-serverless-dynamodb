package inputform.backend.handler

import com.amazonaws.services.lambda.runtime.{Context, LambdaLogger}
import inputform.backend.api.aws.ApiGatewayResponse
import inputform.backend.model.{Item, ItemDao}
import inputform.backend.service.ListItemsService
import inputform.backend.utils.JsonUtils
import org.joda.time.DateTime
import org.mockito.Mockito
import org.mockito.ArgumentMatchers.any
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{FunSuite, Matchers}

import scala.collection.JavaConverters

class ListItemsServiceTest extends FunSuite with Matchers with MockitoSugar {

  private final val ITEM_A: Item = Item("12345", "John", "Doe", "johndoe@mail.com", "abc",
    "Some Options", true, DateTime.now.toString, DateTime.now.toString)

  private final val ITEM_B: Item = Item("12346", "Jane", "Doe", "janedoe@mail.com", "def",
    "Some Options", false, DateTime.now.toString, DateTime.now.toString)


  test("StatusCode: 200 - OK") {
    val service: ListItemsService = new ListItemsService(mockDao())
    val response: ApiGatewayResponse = service.execute(mockInput(), mockContext())
    response.statusCode shouldBe 200
    response.headers shouldBe JavaConverters.mapAsJavaMap(Map.empty)

    val stringToItems: List[Item] = JsonUtils.jsonStringToItemsList(response.body)
    List(ITEM_A, ITEM_B) shouldEqual stringToItems
  }

  private def mockDao(): ItemDao = {
    val itemDao: ItemDao = mock[ItemDao]
    Mockito.when(itemDao.getAll()).thenReturn(List(ITEM_A, ITEM_B))
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
    JavaConverters.mapAsJavaMap(Map.empty)
  }

}
