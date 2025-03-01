package lotto.domain

import lotto.global.Message

data class UserInput(
    val buyAmount: Int,
    val manualLottoCount: Int,
    private val rawManualLotto: List<List<Int>> = listOf(),
) {
    val manualLotto: List<Lotto> = rawManualLotto.map { Lotto.of(it) }
    val totalLottoCount: Int = buyAmount / Lotto.LOTTO_PRICE
    val automaticLottoCount: Int = totalLottoCount - manualLottoCount

    class Builder {
        private var buyAmount: Int? = null
        private var manualLottoCount: Int? = null
        private var rawManualLotto: List<List<Int>>? = null

        fun buyAmount(buyAmount: Int): Int? {
            getValidBuyAmountOrNull(buyAmount) ?: return null
            this.buyAmount = buyAmount
            return buyAmount
        }

        fun manualLottoCount(manualLottoCount: Int): Int? {
            requireNotNull(buyAmount) { "buyAmount$ERR_PROPERTY_NOT_PROVIDED" }
            getValidManualLottoCountOrNull(buyAmount!!, manualLottoCount) ?: return null
            this.manualLottoCount = manualLottoCount
            return manualLottoCount
        }

        fun manualLotto(manualLotto: List<List<Int>>): List<List<Int>>? {
            requireNotNull(manualLottoCount) { "manualLottoCount$ERR_PROPERTY_NOT_PROVIDED" }
            getValidManualLottoSizeOrNull(manualLottoCount!!, manualLotto) ?: return null
            this.rawManualLotto = manualLotto
            return manualLotto
        }

        fun build(): UserInput {
            requireNotNull(buyAmount) { "buyAmount$ERR_PROPERTY_NOT_PROVIDED" }
            requireNotNull(manualLottoCount) { "manualLottoCount$ERR_PROPERTY_NOT_PROVIDED" }
            requireNotNull(rawManualLotto) { "rawManualLotto$ERR_PROPERTY_NOT_PROVIDED" }
            return UserInput(buyAmount!!, manualLottoCount!!, rawManualLotto!!)
        }
    }

    init {
        requireNotNull(getValidBuyAmountOrNull(buyAmount)) { Message.ERR_LESS_THAN_MINIMUM_PRICE.msg }
        requireNotNull(getValidManualLottoCountOrNull(manualLottoCount, buyAmount)) { Message.ERR_TOO_MANY_MANUAL_LOTTO.msg }
        requireNotNull(getValidManualLottoSizeOrNull(manualLottoCount, rawManualLotto)) { Message.ERR_MANUAL_NOT_SUFFICIENT.msg }
    }

    companion object {
        private const val ERR_PROPERTY_NOT_PROVIDED = "프로퍼티가 설정되지 않았습니다"

        fun getValidBuyAmountOrNull(buyAmount: Int): Int? = if (buyAmount >= Lotto.LOTTO_PRICE) buyAmount else null

        fun getValidManualLottoCountOrNull(
            manualLottoCount: Int,
            buyAmount: Int,
        ): Int? = if (manualLottoCount <= buyAmount / Lotto.LOTTO_PRICE) manualLottoCount else null

        fun getValidManualLottoSizeOrNull(
            manualLottoCount: Int,
            manualLotto: List<List<Int>>,
        ): List<List<Int>>? = if (manualLotto.size == manualLottoCount) manualLotto else null
    }
}
