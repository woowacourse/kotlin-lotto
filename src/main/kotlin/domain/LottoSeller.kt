package domain

import domain.PurchaseLottoMoney.Companion.ONE_LOTTO_MONEY

class LottoSeller(private val numberGenerator: RandomGenerator = NumberRandomGenerator()) {
    private fun sellLotto(): Lotto {
        return Lotto(numberGenerator.generate().map { LottoNumber.from(it) }.toSet())
    }

    fun sellAutoTicket(money: PurchaseLottoMoney): Ticket {
        return Ticket(List(money.purchaseCount) { sellLotto() })
    }

    fun sellManualAndAuto(purchaseInfo: LottoPurchaseInfo, requestManualTicket: Ticket): Ticket? {
        if (purchaseInfo.manualCount != requestManualTicket.lottos.size) {
            return null
        }
        val auto = sellAutoTicket(purchaseInfo.autoCount)
        val manual = sellManualTicket(requestManualTicket)
        return Ticket(manual.lottos + auto.lottos)
    }

    private fun sellAutoTicket(count: Int): Ticket =
        if (count == 0) {
            Ticket(emptyList())
        } else {
            sellAutoTicket(PurchaseLottoMoney(count * ONE_LOTTO_MONEY))
        }

    private fun sellManualTicket(ticket: Ticket): Ticket = Ticket(ticket.lottos.toList())
}
