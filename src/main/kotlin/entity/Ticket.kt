package entity

import model.Amount
import model.Lotto

data class Ticket(val userLotteries: List<Lotto>, val amount: Amount)
