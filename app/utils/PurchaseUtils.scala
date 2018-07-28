package utils

import models.Template

object PurchaseUtils {
  def canSavePurchase(template: Template): Boolean = {
    if (template.status.equals("Inactive")) {
      return false
    }
    true
  }
}
