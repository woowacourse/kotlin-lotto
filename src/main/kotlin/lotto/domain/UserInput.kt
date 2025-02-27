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
        requireNotNull(getValidBuyAmountOrNull(buyAmount)) { Message.ERR_LESS_THAN_MINIMUM_PRICE.msg }
        requireNotNull(getValidManualLottoCountOrNull(manualLottoCount, buyAmount)) { Message.ERR_TOO_MANY_MANUAL_LOTTO.msg }
        requireNotNull(getValidManualLottoSizeOrNull(manualLottoCount, rawManualLotto)) { Message.ERR_MANUAL_NOT_SUFFICIENT.msg }
    }

    companion object {
        fun getValidBuyAmountOrNull(buyAmount: Int): Int? = if (buyAmount >= LOTTO_PRICE) buyAmount else null

        fun getValidManualLottoCountOrNull(
            manualLottoCount: Int,
            buyAmount: Int,
        ): Int? = if (manualLottoCount <= buyAmount / LOTTO_PRICE) manualLottoCount else null

        fun getValidManualLottoSizeOrNull(
            manualLottoCount: Int,
            manualLotto: List<List<Int>>,
        ): List<List<Int>>? = if (manualLotto.size == manualLottoCount) manualLotto else null
    }
}
