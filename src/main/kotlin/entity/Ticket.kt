package entity

import model.Amount
import model.Lottery
import model.LotteryPurchasePattern

data class Ticket(
    val userLotteries: List<Lottery>,
    val amount: Amount,
    val lotteryPurchasePattern: LotteryPurchasePattern,
)
