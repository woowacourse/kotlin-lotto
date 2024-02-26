package model

object ProfitStatusDecider {
    private const val ERROR_UNEXPECTED = "예기치 못한 오류가 발생하였습니다."

    fun decide(
        purchaseAmount: Money,
        winningAmount: Money,
    ): ProfitStatus =
        when {
            winningAmount > purchaseAmount -> ProfitStatus.GAIN
            winningAmount < purchaseAmount -> ProfitStatus.LOSS
            winningAmount == purchaseAmount -> ProfitStatus.EVEN
            else -> throw IllegalStateException(ERROR_UNEXPECTED)
        }
}
