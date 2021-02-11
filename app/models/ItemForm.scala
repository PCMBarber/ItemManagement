package models

import play.api.data.Form
import play.api.data.Forms._

case class ItemForm(id: Int = 0, name: String, imgURL: String, description: String, price: Int)
object ItemForm {
  val itemForm = Form(
    mapping(
      "id" -> default(number,0),
      "name" -> nonEmptyText,
      "imgURL" -> nonEmptyText,
      "description" -> nonEmptyText,
      "price" -> number
    ) (Item.apply)(Item.unapply)
  )
}