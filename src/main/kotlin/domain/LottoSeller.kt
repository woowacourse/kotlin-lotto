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
            println(ERROR_MANUAL_COUNT_NOT_EQUAL.format(purchaseInfo.manualCount, requestManualTicket.lottos.size))
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

    companion object {
        private const val ERROR_MANUAL_COUNT_NOT_EQUAL = "[ERROR] 실제 수동 구매 개수는 %d이고 요청한 수동 구매의 로또 개수는 %d개 입니다."
    }
}
