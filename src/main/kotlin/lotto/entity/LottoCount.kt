package lotto.entity

class LottoCount(val value: Int) {
    companion object {
        private const val MESSAGE_GREATER_THAN_PURCHASE_MONEY = "구매할 로또의 수가 구매한 수량보다 많습니다. 구매 할 수 : %d"
        fun from(value: Int, purchaseMoney: PurchaseMoney): LottoCount {
            val lottoAutoCount = purchaseMoney.calculateLottoCount()
            require(value <= lottoAutoCount.value) {
                MESSAGE_GREATER_THAN_PURCHASE_MONEY.format(value)
            }
            return LottoCount(value)
        }
    }
}
