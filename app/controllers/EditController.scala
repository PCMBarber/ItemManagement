package controllers

import dao.ItemDAO
import models.{Item, ItemForm}
import play.api.i18n.{I18nSupport, MessagesApi}

import javax.inject._
import play.api.mvc._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.Results.Redirect
import play.mvc.Controller

import scala.concurrent.ExecutionContext.Implicits.global
import javax.inject.Inject
import scala.util.{Failure, Success}

class EditController @Inject()(messagesApi: MessagesApi, environment: play.api.Environment, cc: ControllerComponents) extends AbstractController(cc) with I18nSupport{

  def itemDetails(id: String) = Action.async { implicit request =>
    ItemDAO readByID(id.toInt) map{
      result => Ok(views.html.itemDetails(result.get)(ItemForm.itemForm))
    }
  }

  def editItem = Action { implicit request =>
    ItemForm.itemForm.bindFromRequest.fold({ _ =>
      Redirect("/")
    }, { widget =>
      edit(widget)
      Redirect("/")
    })
  }

  def edit(item: Item): Unit = {
    ItemDAO update(item) onComplete {
      case Success(value) =>
        println(value)
      case Failure(exception) =>
        exception.printStackTrace()
    }
  }
}
