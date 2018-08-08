package services

import models._
import play.api.Logger
import utils.PurchaseUtils

object PurchaseService {

  def savePurchase(userId: String, templateId: String): String = {
    val template = Template.findById(templateId).getOrElse(throw new Exception("Template not found"))
    val user = User.findById(userId).getOrElse(throw new Exception("User not found"))
    if (!PurchaseUtils.canSavePurchase(template)) {
      Logger.info("Erro - Save purchase")
      return "Erro - Save purchase"
    }
    val value = PurchaseUtils.calcPurchaseValue(template.price)
    Purchase.savePurchase(Purchase("1", value, user, template))
    "Purchase saved successfully!"
  }

}
