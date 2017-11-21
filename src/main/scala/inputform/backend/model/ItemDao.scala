package inputform.backend.model

import org.joda.time.DateTime

trait ItemDao {
  def get(id: String): Item
  def getAll(): List[Item]
  def put(item: Item)
}

class ItemDaoImpl extends ItemDao {

  private final val ITEM: Item = Item("12345", "John", "Doe", "johndoe@mail.com", "abc", "", "", DateTime.now.toString,
    DateTime.now.toString)

  def get(id: String): Item = {
    ITEM
  }

  def getAll(): List[Item] = {
    List(ITEM)
  }

  def put(item: Item): Unit = {
  }
}
