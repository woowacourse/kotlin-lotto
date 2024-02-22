package domain

import domain.model.Money

class Cashier {
    fun toTicketQuantity(money: Money): Int = (money.amount / LOTTO_PRICE).toInt()

    companion object {
        const val LOTTO_PRICE = 1000
    }
}
