package domain

class LottoSeller(private val numberGenerator: RandomGenerator = NumberRandomGenerator()) {
    private fun sellLotto(): Lotto {
        return Lotto(numberGenerator.generate().map { LottoNumber.from(it) }.toSet())
    }

    fun sellLottos(money: PurchaseLottoMoney): Ticket {
        return Ticket(List(money.purchaseCount) { sellLotto() })
    }

    fun sellManualAndAuto(purchaseInfo: LottoPurchaseInfo, requestManualNumbers: List<Lotto>) {
        require(purchaseInfo.manualCount == requestManualNumbers.size) {
            ERROR_MANUAL_COUNT_NOT_EQUAL.format(purchaseInfo.manualCount, requestManualNumbers.size)
        }
    }

    companion object {
        private const val ERROR_MANUAL_COUNT_NOT_EQUAL = "[ERROR] 실제 수동 구매 개수는 %d이고 요청한 수동 구매의 로또 개수는 %d개 입니다."
    }
}
