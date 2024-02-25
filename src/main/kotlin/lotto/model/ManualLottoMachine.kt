package lotto.model

class ManualLottoMachine(val buyCount: Int, val lottoBuyPrice: LottoBuyPrice) {
    init {
        require(buyCount <= lottoBuyPrice.getBuyCount()) { PRICE_ERROR_MESSAGE }
    }

    fun createLottoFrom(manualInputNumbers: List<Int>): Lotto {
        return Lotto(manualInputNumbers.map(LottoNumber::of))
    }

    companion object {
        private const val PRICE_ERROR_MESSAGE = "수동으로 구입하는 로또의 가격은 로또 구입 가격이하여야 합니다."
    }
}
