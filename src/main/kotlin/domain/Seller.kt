package domain

import model.Count
import model.Payment

class Seller(private val payment: Payment, private val lottoMachine: LottoMachine) {

    fun getLottoCount(): Count = Count(payment.money / ONE_TICKET_PRICE)
    fun getLotto() = lottoMachine.makeLotto(getLottoCount())

    companion object {
        private const val ONE_TICKET_PRICE = 1000
    }
}
