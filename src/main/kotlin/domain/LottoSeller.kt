package domain

import domain.PurchaseLottoMoney.Companion.ONE_LOTTO_MONEY

class LottoSeller(private val numberGenerator: RandomGenerator = NumberRandomGenerator()) {
    private fun sellLotto(): Lotto {
        return Lotto(numberGenerator.generate().map { LottoNumber.from(it) }.toSet())
    }

    fun sellLottos(money: PurchaseLottoMoney): Ticket {
        return Ticket(List(money.purchaseCount) { sellLotto() })
    }

    fun sellManualAndAuto(purchaseInfo: LottoPurchaseInfo, requestManualNumbers: List<Lotto>): Ticket? {
        if (purchaseInfo.manualCount != requestManualNumbers.size) {
            println(ERROR_MANUAL_COUNT_NOT_EQUAL.format(purchaseInfo.manualCount, requestManualNumbers.size))
            return null
        }
        val manual = sellManualLottos(requestManualNumbers)
        val auto = sellAutoLottos(purchaseInfo.autoCount)
        return Ticket(manual.lottos + auto.lottos)
    }

    private fun sellManualLottos(requestManualNumbers: List<Lotto>): Ticket = Ticket(requestManualNumbers)

    private fun sellAutoLottos(count: Int): Ticket =
        if (count == 0) Ticket(emptyList())
        else sellLottos(PurchaseLottoMoney(count * ONE_LOTTO_MONEY))

    companion object {
        private const val ERROR_MANUAL_COUNT_NOT_EQUAL = "[ERROR] 실제 수동 구매 개수는 %d이고 요청한 수동 구매의 로또 개수는 %d개 입니다."
    }
}
