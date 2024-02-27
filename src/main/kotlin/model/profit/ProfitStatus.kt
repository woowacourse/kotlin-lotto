package model.profit

import model.Money

enum class ProfitStatus(
    val status: String,
) {
    GAIN("이득"),
    LOSS("손해"),
    EVEN("본전"), ;

    companion object {
        fun decide(
            purchaseAmount: Money,
            winningAmount: Money,
        ): ProfitStatus =
            when {
                winningAmount > purchaseAmount -> GAIN
                winningAmount < purchaseAmount -> LOSS
                winningAmount == purchaseAmount -> EVEN
                else -> throw IllegalStateException("예기치 못한 오류가 발생하였습니다.")
            }
    }
}
