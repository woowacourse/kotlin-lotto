package lotto.model

import lotto.constant.LottoConstant

class LottoMachine(private val price: String) {
    init {
        val priceAsInt = price.toIntOrNull()
        require(priceAsInt != null && priceAsInt >= LottoConstant.PRICE) { PRICE_ERROR_MESSAGE }
    }

    fun createLottos() {
        val count = price.toInt() / LottoConstant.PRICE
        List(count) {
            Lotto(
                (LottoConstant.NUMBER_RANGE).shuffled().take(LottoConstant.SIZE).sorted()
                    .map { LottoNumber(it) },
            )
        }
    }

    companion object {
        private const val PRICE_ERROR_MESSAGE = "구입 금액은 자연수이면서 1000 이상이여야 합니다."
    }
}
