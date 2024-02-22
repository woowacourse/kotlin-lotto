package lotto.model

import lotto.constant.LottoConstant

class LottoMachine(private val price: String) {
    init {
        require(price.toIntOrNull()?.let { it >= LottoConstant.PRICE } == true) { PRICE_ERROR_MESSAGE }
    }

    fun createLottos() =
        List(lottoCounts()) {
            Lotto(
                (LottoConstant.NUMBER_RANGE).shuffled().take(LottoConstant.SIZE).sorted()
                    .map { LottoNumber(it) },
            )
        }

    fun lottoCounts(): Int = price.toInt() / LottoConstant.PRICE

    companion object {
        private const val PRICE_ERROR_MESSAGE = "구입 금액은 자연수이면서 1000 이상이여야 합니다."
    }
}
