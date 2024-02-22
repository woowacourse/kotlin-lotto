package entity

import model.Amount
import model.Lottery

data class Ticket(val userLotteries: List<Lottery>, val amount: Amount)
