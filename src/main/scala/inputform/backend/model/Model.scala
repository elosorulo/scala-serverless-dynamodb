package inputform.backend.model

import org.joda.time.DateTime

case class Item(id: String, firstName: String, lastName: String, email: String, comments: String, options: String,
               checked: String, createdAt: String, updatedAt: String)