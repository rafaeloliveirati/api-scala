package models

case class Purchase(transaction: String, value: Double, user: User, template: Template)

object PurchaseHandler {

  def savePurchase(): Unit = {
    println("salvou")
  }

}