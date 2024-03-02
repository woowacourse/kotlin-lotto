package entity

import model.Lottery
import model.PurchaseInformation

data class Ticket(
    val userLotteries: List<Lottery>,
    val purchaseInformation: PurchaseInformation,
)
