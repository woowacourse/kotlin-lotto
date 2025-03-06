package lotto.view

import lotto.domain.model.Lotto
import lotto.domain.model.Rank

object Message {
    fun purchaseResult(
        manualCount: Int,
        autoCount: Int,
    ) = "\n수동으로 ${manualCount}장, 자동으로 ${autoCount}장을 구매했습니다."

    fun winningStatsHeader() = "\n당첨 통계\n---------"

    fun winningStats(
        rankCount: Int,
        bonus: Boolean,
        winningMoney: Int,
        count: Int,
    ): String {
        val bonusText = if (bonus) ", 보너스 볼 일치" else ""
        return "${rankCount}개 일치$bonusText (${winningMoney}원) - ${count}개"
    }

    fun earningRate(rate: Double) = "총 수익률은 %.2f입니다.".format(rate)

    fun errorInvalidLotto() = "[ERROR] 잘못된 로또 번호입니다. 다시 입력하세요."

    fun errorInvalidAmount() = "[ERROR] 금액은 0 이상이어야 합니다. 다시 입력하세요."

    fun errorInvalidWinningNumbers() = "[ERROR] 잘못된 당첨 번호입니다. 다시 입력하세요."

    fun errorInvalidBonusNumber() = "[ERROR] 보너스 번호가 잘못되었습니다. 다시 입력하세요."

    fun errorCountExceeded() = "[ERROR] 수동 로또 개수가 전체 구매 가능 개수를 초과할 수 없습니다. 다시 입력하세요."
}

class OutputView {
    fun printPurchaseResult(
        manualLottos: List<Lotto>,
        autoLottos: List<Lotto>,
    ) {
        println(Message.purchaseResult(manualLottos.size, autoLottos.size))
        autoLottos.forEach { println(it.numberList.map { num -> num.value }) }
    }

    fun printResult(
        sortedResults: Map<Rank, Int>,
        earningRate: Double,
    ) {
        println(Message.winningStatsHeader())
        sortedResults.forEach { (rank, count) ->
            println(Message.winningStats(rank.countOfMatch, rank.matchBonus, rank.winningMoney, count))
        }
        println(Message.earningRate(earningRate))
    }

    fun printErrorMessage(message: String) {
        println(message)
    }
}
