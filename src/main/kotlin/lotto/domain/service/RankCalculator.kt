package lotto.domain.service

import lotto.domain.model.Rank

class RankCalculator {
    fun earningMoney(winningList: Map<Rank, Int>): Int {
        var money = 0
        winningList.forEach {
            money += it.key.winningMoney * it.value
        }
        return money
    }

    fun calculateEarningRate(
        inputMoney: Int,
        earningMoney: Int,
    ): Double {
        require(inputMoney != 0) { "[ERROR] 입력 금액이 0입니다" }
        return earningMoney.toDouble() / inputMoney.toDouble()
    }
}
