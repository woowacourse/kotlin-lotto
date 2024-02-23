package lotto.domain

import lotto.domain.model.Money
import lotto.util.Constants.LOTTO_PRICE

class Cashier {
    fun toTicketQuantity(money: Money): Int = (money.amount / LOTTO_PRICE).toInt()
}
