package controllers

import dao.ItemDAO
import models.{Item, ItemForm}

import javax.inject._
import play.api.mvc._
import play.api.i18n.{I18nSupport, MessagesApi}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {

  def index = Action.async {
    ItemDAO.readAll map {
      itemList => Ok(views.html.index(itemList))
    } recover {
        case error: Exception => InternalServerError("database failed")
      }
  }

  def deleteItem(id: String) = Action.async { implicit request =>
    ItemDAO.delete(id.toInt) map{
      result => Redirect("/")
    } recover {
      case e: Exception =>
        InternalServerError("database failed")
    }
  }

}
