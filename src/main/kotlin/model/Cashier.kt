package model

class Cashier {
    fun toTicketQuantity(money: Money): Int = money.value / LOTTO_PRICE

    companion object {
        const val LOTTO_PRICE = 1000
    }
}
