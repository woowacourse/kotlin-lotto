package model

data class Ticket(
    val userLotteries: List<Lottery>,
    val purchaseInformation: PurchaseInformation,
)
