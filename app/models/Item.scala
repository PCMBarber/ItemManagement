package models

import play.api.data.Form
import play.api.data.Forms._
import slick.jdbc.MySQLProfile.api._

case class Item(id: Int = 0, name: String, imgURL: String, description: String, price: Int) {
  def getId() = id
  def getName() = name
  def getImgUrl() = imgURL
  def getDescription() = description
  def getPrice() = price
}
case class Items(tag: Tag) extends Table[Item](tag, "items") {
  def id = column[Int]("Item_ID", O.PrimaryKey, O.AutoInc)
  def name = column[String]("Item_Name")
  def imgURL = column[String]("Item_IMG_URL")
  def description = column[String]("Item_Description")
  def price = column[Int]("Item_Price")
  def * = (id, name, imgURL, description, price) <> (Item.tupled, Item.unapply)
}