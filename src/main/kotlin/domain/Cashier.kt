package domain

import domain.model.Money
import util.Constants.LOTTO_PRICE

class Cashier {
    fun toTicketQuantity(money: Money): Int = (money.amount / LOTTO_PRICE).toInt()
}
