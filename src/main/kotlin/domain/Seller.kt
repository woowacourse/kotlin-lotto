package domain

import model.LottoCount
import model.Payment

class Seller(private val payment: Payment) {

    fun getLottoCount(): LottoCount = LottoCount(payment.money / ONE_TICKET_PRICE)

    companion object {
        private const val ONE_TICKET_PRICE = 1000
    }
}
