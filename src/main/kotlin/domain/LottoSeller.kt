package domain

import domain.PurchaseLottoMoney.Companion.ONE_LOTTO_MONEY

class LottoSeller(private val numberGenerator: RandomGenerator = NumberRandomGenerator()) {
    private fun sellLotto(): Lotto {
        return Lotto(numberGenerator.generate().map { LottoNumber.from(it) }.toSet())
    }

    fun sellLottos(money: PurchaseLottoMoney): Ticket {
        return Ticket(List(money.purchaseCount) { sellLotto() })
    }

    fun sellManualAndAuto(purchaseInfo: LottoPurchaseInfo, requestManualTicket: Ticket): Ticket? {
        if (purchaseInfo.manualCount != requestManualTicket.lottos.size) {
            return null
        }
        val auto = sellAutoTicket(purchaseInfo.autoCount)
        return Ticket(requestManualTicket.lottos + auto.lottos)
    }

    private fun sellAutoTicket(count: Int): Ticket =
        if (count == 0) {
            Ticket(emptyList())
        } else {
            sellLottos(PurchaseLottoMoney(count * ONE_LOTTO_MONEY))
        }
}
