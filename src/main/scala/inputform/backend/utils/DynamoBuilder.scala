package inputform.backend.utils

import com.amazonaws.services.dynamodbv2.{AmazonDynamoDB, AmazonDynamoDBClientBuilder}

trait DynamoBuilder {
  def build(): AmazonDynamoDB
}

class DynamoBuilderImpl extends DynamoBuilder {
  override def build(): AmazonDynamoDB = {
    Logger.info(s"Building DynamoDB Client.")
    AmazonDynamoDBClientBuilder
      .standard()
      .build()
  }
}
