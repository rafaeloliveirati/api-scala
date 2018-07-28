package services

import com.mongodb.casbah.Imports.ObjectId
import models._
import utils.PurchaseUtils

object PurchaseService {

  def savePurchase(userId: String, templateId: String): Unit = {
    val template = Template.findById(templateId)
    val user = User.findById(userId)
    if (!PurchaseUtils.canSavePurchase(template)) {
      print("Erro - Save purchase")
    }
    val value = PurchaseUtils.calcPurchaseValue(template.price)
    val purchase = Purchase(new ObjectId, "1", value, user, template)
    Purchase.savePurchase(purchase)
  }

}
