package lotto.model

import kotlin.math.floor

class WinningLotto(val lotto: Lotto, val bonusNumber: LottoNumber) {
    init {
        require(!lotto.contains(bonusNumber)) {
            INVALID_DUPLICATE_BONUS_NUMBER
        }
    }

    fun calculatePrizeCount(lottos: List<Lotto>) =
        lottos
            .map { it.compare(lotto, bonusNumber) }
            .groupBy { it }
            .mapValues { it.value.size }

    fun calculateProfitRatio(
        lottos: List<Lotto>,
        purchasePrice: Int,
    ): Double {
        val totalPrizeAmount =
            calculatePrizeCount(lottos)
                .map { (lottoPrize, count) ->
                    lottoPrize.amount * count.toLong()
                }.sum()

        return (totalPrizeAmount.toDouble() / purchasePrice).roundTwoDecimal()
    }

    private fun Double.roundTwoDecimal() = floor(this * 100) / 100

    companion object {
        private const val INVALID_DUPLICATE_BONUS_NUMBER = "당첨 번호와 중복되지 않는 보너스 번호를 입력해 주세요."
    }
}
