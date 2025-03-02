package lotto.domain.service

import lotto.domain.model.Rank

class RankCalculator {
    fun earningMoney(winningList: List<Rank>): Int = winningList.sumOf { it.winningMoney }

    fun calculateEarningRate(
        inputMoney: Int,
        earningMoney: Int,
    ): Double {
        require(inputMoney != 0) { "[ERROR] 입력 금액이 0입니다" }
        return earningMoney.toDouble() / inputMoney.toDouble()
    }
}
