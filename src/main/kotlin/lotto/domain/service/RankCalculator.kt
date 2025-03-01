package lotto.domain.service

import lotto.domain.model.Rank

class RankCalculator {
    fun earningMoney(winningList: List<Rank>): Int = winningList.sumOf { it.winningMoney }

    fun calculateEarningRate(
        inputMoney: Int,
        earningMoney: Int,
    ): Double {
        require(inputMoney != 0) { INPUT_MONEY_ZERO }
        return earningMoney.toDouble() / inputMoney.toDouble()
    }

    companion object {
        const val INPUT_MONEY_ZERO = "[Error] 입력 금액이 0입니다"
    }
}
