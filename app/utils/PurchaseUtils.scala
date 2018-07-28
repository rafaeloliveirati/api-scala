package utils

import models.Template

object PurchaseUtils {
  def canSavePurchase(template: Template): Boolean = {
    if (template.status.equals("Inactive")) {
      return false
    }
    true
  }

  def calcPurchaseValue(templateValue: Double): Double = {
    //Rule of calc purchase value, add %
    templateValue + templateValue * 0.10
  }
}
