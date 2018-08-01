package services

import models._

object PurchaseService {

  def savePurchase(userId: String, templateId: String): Unit = {
//    val template = Template.findById(templateId)
    val user = User.findUsers()
    //    if (!PurchaseUtils.canSavePurchase(template)) {
    //      print("Erro - Save purchase")
    //    }
    //    val value = PurchaseUtils.calcPurchaseValue(template.price)
    //    val purchase = Purchase("1", value, user, template)
    //    Purchase.savePurchase(purchase)
  }

}
