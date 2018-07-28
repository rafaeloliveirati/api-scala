package services

import com.mongodb.casbah.Imports.ObjectId
import models._

object PurchaseService {

  def savePurchase(): Unit = {
    val user = User.findById("5b57dfe78432b20718d14ea6")
    val template = Template.findById("5b5b69508432b22c28583e25")
    val purchase = Purchase(new ObjectId, "fdsafdsa", 20, user, template)
    val temp = Template(new ObjectId, "fdsafdas", 1, "ok")
    //    Template.save(temp)
    Purchase.savePurchase(purchase)
  }
}
