package inputform.backend.handler.create

import com.amazonaws.services.lambda.runtime.Context
import inputform.backend.api.aws.ApiGatewayResponse
import inputform.backend.handler.CreateItemHandler
import inputform.backend.model.Item
import org.joda.time.DateTime
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{FunSuite, Matchers}

import scala.collection.JavaConverters

class CreateItemServiceTest extends FunSuite with Matchers with MockitoSugar {
  private final val ITEM: Item = Item("12345", "John", "Doe", "johndoe@mail.com", "abc", "", "", DateTime.now.toString,
    DateTime.now.toString)

  test("StatusCode: 200 - OK") {
    val create: CreateItemHandler = new CreateItemHandler
    val response: ApiGatewayResponse = create.handleRequest(JavaConverters.mapAsJavaMap(Map.empty), mock[Context])

    response.body shouldBe ""
    response.statusCode shouldBe 200
    response.headers shouldBe JavaConverters.mapAsJavaMap(Map.empty)
  }

  test("StatusCode: 400 - Bad Request") {

  }

  test("StatusCode: 404 - Not Found") {

  }
}
