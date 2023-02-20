package lotto.entity

class PurchaseMoney(value: Int) : Money(value) {
    init {
        require(value >= LOTTO_PRICE) { ERROR_MESSAGE_PURCHASE_LESS_THAN_LOTTO.format(value) }
    }

    fun calculateLottoCount(manualLottoCount: LottoCount = LottoCount(0)): LottoCount = LottoCount((value / LOTTO_PRICE) - manualLottoCount.value)

    companion object {
        const val ERROR_MESSAGE_PURCHASE_LESS_THAN_LOTTO = "구입 금액이 로또 가격보다 적습니다. 입력된 구입 금액은 %d원 입니다."
    }
}
