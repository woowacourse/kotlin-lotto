package lotto.model

import lotto.model.lottery.Lottery
import lotto.model.puchaseinformation.PurchaseInformation

data class Ticket(
    val userLotteries: List<Lottery>,
    val purchaseInformation: PurchaseInformation,
)
