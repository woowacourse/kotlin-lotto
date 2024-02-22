package lotto.model

import lotto.constant.LottoConstant

class LottoMachine(private val price: Int) {
    init {
        require(price >= LottoConstant.PRICE) { PRICE_ERROR_MESSAGE }
    }

    fun createLottos(): List<Lotto> {
        val count = price / LottoConstant.PRICE
        return List(count) {
            createLotto()
        }
    }

    private fun createLotto() =
        Lotto(
            (LottoConstant.NUMBER_RANGE).shuffled().take(LottoConstant.SIZE).sorted()
                .map { LottoNumber(it) },
        )

    companion object {
        private const val PRICE_ERROR_MESSAGE = "구입 금액은 자연수이면서 1000 이상이여야 합니다."
    }
}
