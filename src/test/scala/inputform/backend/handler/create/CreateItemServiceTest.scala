package inputform.backend.handler.create

import com.amazonaws.services.lambda.runtime.{Context, LambdaLogger}
import inputform.backend.api.aws.ApiGatewayResponse
import inputform.backend.handler.CreateItemHandler
import inputform.backend.model.Item
import inputform.backend.utils.JsonUtils
import org.joda.time.DateTime
import org.mockito.Mockito
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{FunSuite, Matchers}

import scala.collection.JavaConverters

class CreateItemServiceTest extends FunSuite with Matchers with MockitoSugar {
  private final val ITEM: Item = Item("12345", "John", "Doe", "johndoe@mail.com", "abc", "", false, DateTime.now.toString,
    DateTime.now.toString)

  test("StatusCode: 200 - OK") {
    val create: CreateItemHandler = new CreateItemHandler
    val input: Map[String, Object] = Map("body" -> JsonUtils.itemToJsonString(ITEM))
    val response: ApiGatewayResponse = create.handleRequest(JavaConverters.mapAsJavaMap(input), mockContext())

    //response.body shouldBe ""
    //response.statusCode shouldBe 200
    response.headers shouldBe JavaConverters.mapAsJavaMap(Map.empty)
  }

  private def mockContext(): Context = {
    val context = mock[Context]
    val logger = mock[LambdaLogger]
    Mockito.doNothing().when(logger).log(org.mockito.ArgumentMatchers.any[String])
    Mockito.when(context.getLogger).thenReturn(logger)
    Mockito.when(context.getRemainingTimeInMillis).thenReturn(1000)
    context
  }

  test("StatusCode: 400 - Bad Request") {

  }

  test("StatusCode: 404 - Not Found") {

  }
}
