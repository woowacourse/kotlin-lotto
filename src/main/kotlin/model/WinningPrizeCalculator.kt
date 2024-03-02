package lotto.model

object WinningPrizeCalculator {
    private const val ERROR_PREFIX = "[ERROR] "
    private const val PURCHASE_UNIT = 1000
    private const val ERROR_NUMBER_RANGE_MESSAGE = "${ERROR_PREFIX}1000원 이상의 값만 입력 가능합니다."

    fun calculateProfitRate(
        purchaseAmount: Int,
        winningResult: Map<WinningRank, Int>,
    ): Double {
        val profitAmount = calculateProfitAmount(winningResult)
        return (profitAmount / purchaseAmount).toDouble()
    }

    private fun calculateProfitAmount(winningResult: Map<WinningRank, Int>): Int =
        winningResult.entries.sumOf {
            it.key.prize * (it.value)
        }.toInt()

    private fun validatePurchaseAmountRange(purchaseAmount: Int) {
        require(purchaseAmount >= PURCHASE_UNIT) { ERROR_NUMBER_RANGE_MESSAGE }
    }
}
