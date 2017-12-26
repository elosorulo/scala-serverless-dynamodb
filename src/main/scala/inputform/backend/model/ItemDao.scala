package inputform.backend.model

import javax.inject.Inject

import com.amazonaws.services.dynamodbv2.{AmazonDynamoDB, AmazonDynamoDBClientBuilder}
import com.gu.scanamo.error.{DynamoReadError, NoPropertyOfType}
import com.gu.scanamo.syntax._
import com.gu.scanamo.{Scanamo, Table}
import inputform.backend.utils.{DynamoBuilder, Logger}
import org.joda.time.DateTime

trait ItemDao {
  def get(id: String): Item
  def getAll(): List[Item]
  def put(item: Item)
}

class ItemDaoImpl @Inject() (dynamoBuilder: DynamoBuilder) extends ItemDao {

  private final val ENV_TABLE_NAME = "DYNAMODB_TABLE"

  def get(id: String): Item = {
    Logger.info(s"Initializing table.")
    val table: Table[Item] = Table[Item](scala.util.Properties.envOrNone(ENV_TABLE_NAME).get)
    Logger.info(s"Executing dynamoDB get Operation with id: " + id)
    Scanamo.exec(dynamoBuilder.build())(table.get('id -> id)).map(o => readOutput(o)).get
  }

  def readOutput(output: Either[DynamoReadError, Item]): Item = {
    output match {
      case Right (x) => x
      case Left (x) =>
      Logger.error (x.toString)
      throw InputFormException (1004, "Unable to retrieve item from dynamodb.")
    }
  }

  def getAll(): List[Item] = {
    Logger.info(s"Initializing table.")
    val table: Table[Item] = Table[Item](scala.util.Properties.envOrNone(ENV_TABLE_NAME).get)
    Logger.info(s"Executing dynamoDB scan Operation")
    Scanamo.exec(dynamoBuilder.build())(table.scan()).map(o => readOutput(o))
  }

  def put(item: Item): Unit = {
    Logger.info(s"Initializing table.")
    val table: Table[Item] = Table[Item](scala.util.Properties.envOrNone(ENV_TABLE_NAME).get)
    val timestamp = DateTime.now().getMillis.toString
    Logger.info(s"Executing dynamoDB scan Operation")
    Scanamo.exec(dynamoBuilder.build())(table.put(item.copy(createdAt = timestamp, updatedAt = timestamp)))
  }
}
