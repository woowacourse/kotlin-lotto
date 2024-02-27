package model.profit

import model.Money

class ProfitStatusDecider {
    fun decide(
        purchaseAmount: Money,
        winningAmount: Money,
    ): ProfitStatus =
        when {
            winningAmount > purchaseAmount -> ProfitStatus.GAIN
            winningAmount < purchaseAmount -> ProfitStatus.LOSS
            winningAmount == purchaseAmount -> ProfitStatus.EVEN
            else -> throw IllegalStateException("예기치 못한 오류가 발생하였습니다.")
        }
}
