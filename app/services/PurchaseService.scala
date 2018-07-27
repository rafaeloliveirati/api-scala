package services

import models.{PurchaseHandler, TemplateHandler, UserHandler}


object PurchaseService {

  def savePurchase(): Unit = {
    val user = UserHandler.findById("5b57dfe78432b20718d14ea6")
    val template = TemplateHandler.findById("5b5b69508432b22c28583e25")
    PurchaseHandler.savePurchase()
  }
}
