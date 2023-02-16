package domain

import model.Count
import model.Payment

class Seller(private val payment: Payment) {

    fun getLottoCount(): Count = Count(payment.money / ONE_TICKET_PRICE)

    companion object {
        private const val ONE_TICKET_PRICE = 1000
    }
}
