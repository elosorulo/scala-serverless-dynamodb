package inputform.backend.config

import inputform.backend.model.{ItemDao, ItemDaoImpl}
import inputform.backend.service.{CreateItemService, GetItemService, ListItemsService, Service}
import inputform.backend.utils.{DynamoBuilder, DynamoBuilderImpl}
import net.codingwell.scalaguice.ScalaModule


class ListItemsModule extends ScalaModule {
  override def configure(): Unit = {
    bind[ItemDao].to[ItemDaoImpl].asEagerSingleton()
    bind[Service].to[ListItemsService].asEagerSingleton()
    bind[DynamoBuilder].to[DynamoBuilderImpl].asEagerSingleton()
  }
}

class GetItemModule extends ScalaModule {
  override def configure(): Unit = {
    bind[ItemDao].to[ItemDaoImpl].asEagerSingleton()
    bind[Service].to[GetItemService].asEagerSingleton()
    bind[DynamoBuilder].to[DynamoBuilderImpl].asEagerSingleton()
  }
}

class CreateItemModule extends ScalaModule {
  override def configure(): Unit = {
    bind[ItemDao].to[ItemDaoImpl].asEagerSingleton()
    bind[Service].to[CreateItemService].asEagerSingleton()
    bind[DynamoBuilder].to[DynamoBuilderImpl].asEagerSingleton()
  }
}