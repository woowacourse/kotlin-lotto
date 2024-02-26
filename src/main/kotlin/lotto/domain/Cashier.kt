package lotto.domain

import lotto.domain.model.Money

class Cashier {
    fun calculateQuantity(money: Money, pricePerUnit: Int): Int = (money.amount / pricePerUnit).toInt()
}
