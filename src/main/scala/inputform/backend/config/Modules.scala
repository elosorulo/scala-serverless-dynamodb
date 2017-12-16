package inputform.backend.config

import inputform.backend.model.{ItemDao, ItemDaoImpl}
import inputform.backend.service.{CreateItemService, GetItemService, ListItemsService, Service}
import net.codingwell.scalaguice.ScalaModule


class ListItemsModule extends ScalaModule {
  override def configure(): Unit = {
    bind[ItemDao].to[ItemDaoImpl].asEagerSingleton()
    bind[Service].to[ListItemsService].asEagerSingleton()
  }
}

class GetItemModule extends ScalaModule {
  override def configure(): Unit = {
    bind[ItemDao].to[ItemDaoImpl].asEagerSingleton()
    bind[Service].to[GetItemService].asEagerSingleton()
  }
}

class CreateItemModule extends ScalaModule {
  override def configure(): Unit = {
    bind[ItemDao].to[ItemDaoImpl].asEagerSingleton()
    bind[Service].to[CreateItemService].asEagerSingleton()
  }
}