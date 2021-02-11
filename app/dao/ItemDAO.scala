package dao

import models.{Item, Items}
import slick.jdbc.MySQLProfile.backend.Database
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object ItemDAO {

  lazy val db: Database = Database.forConfig("mysqlDB")
  lazy val table: TableQuery[Items] = TableQuery[Items]

  def readAll: Future[Seq[Item]] = {
    db.run(table.result)
  }

  def readByID(id: Int): Future[Option[Item]] = {
    db.run(table.filter(_.id === id).result.headOption)
  }

  def create(item: Item): Future[String] = {
    db.run(table += item).map(res => "Item successfully added").recover {
      case ex: Exception => ex.getCause.getMessage
    }
  }


  def update(item: Item): Future[String] = {
    db.run(table.insertOrUpdate(item)).map(res => "Item successfully updated").recover {
      case ex: Exception => ex.getCause.getMessage
    }
  }

  def delete(id: Int): Future[Int] = {
    db.run(table.filter(_.id === id).delete)
  }
}
