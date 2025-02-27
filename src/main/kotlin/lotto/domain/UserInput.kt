package lotto.domain

import lotto.global.Message

data class UserInput(
    val buyAmount: Int,
    val manualLottoCount: Int,
    private val rawManualLotto: List<List<Int>> = listOf(),
) {
    val manualLotto: List<Lotto> = rawManualLotto.map { Lotto(it.map { LottoNumber.of(it) }) }
    val totalLottoCount: Int = buyAmount / LOTTO_PRICE
    val automaticLottoCount: Int = totalLottoCount - manualLottoCount

    init {
        requireBuyAmount(buyAmount)
        requireMaxManualLottoCount(manualLottoCount, buyAmount)
        requireIsEqualToManualLottoSize(manualLottoCount, rawManualLotto)
    }

    companion object {
        fun requireBuyAmount(buyAmount: Int) {
            require(buyAmount >= LOTTO_PRICE) { Message.ERR_LESS_THAN_MINIMUM_PRICE.msg }
        }

        fun requireMaxManualLottoCount(
            manualLottoCount: Int,
            buyAmount: Int,
        ) {
            require(manualLottoCount <= buyAmount / LOTTO_PRICE) { Message.ERR_TOO_MANY_MANUAL_LOTTO.msg }
        }

        fun requireIsEqualToManualLottoSize(
            manualLottoCount: Int,
            manualLotto: List<List<Int>>,
        ) {
            require(manualLotto.size == manualLottoCount) { Message.ERR_MANUAL_NOT_SUFFICIENT.msg }
        }
    }
}
