package lotto.view

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.util.Rank

class OutputView {
    fun printPurchasedLottos(
        lottos: List<Lotto>,
        manualLottoCount: Int,
        autoLottoCount: Int,
    ) {
        println(MESSAGE_BUY_LOTTO.format(manualLottoCount, autoLottoCount))
        lottos.forEach { lotto ->
            printLottoNumber(lotto.numbers)
        }
    }

    private fun printLottoNumber(numbers: List<LottoNumber>) {
        println(numbers.map { it.number })
    }

    fun printWinningStats() {
        println(MESSAGE_WINNING_STATS)
    }

    fun printWinningStatsByRank(
        state: Rank,
        count: Int,
    ) {
        if (state == Rank.SECOND) {
            printWinningStatWithBonusBall(state, count)
        } else if (state != Rank.NONE) {
            printWinningStatWIthNoBonusBall(state, count)
        }
    }

    private fun printWinningStatWIthNoBonusBall(
        state: Rank,
        count: Int,
    ) {
        println(MESSAGE_MATCH_COUNT.format(state.countOfMatch, state.price, count))
    }

    private fun printWinningStatWithBonusBall(
        state: Rank,
        count: Int,
    ) {
        println(MESSAGE_MATCH_COUNT_WITH_BONUS_BALL.format(state.countOfMatch, state.price, count))
    }

    fun printProfit(profit: Double) {
        println(MESSAGE_PROFIT.format(profit))
    }

    companion object {
        private const val MESSAGE_BUY_LOTTO = "수동으로 %d장, 자동으로 %d개를 구매했습니다."
        private const val MESSAGE_WINNING_STATS = "\n당첨 통계\n---------"
        private const val MESSAGE_MATCH_COUNT = "%d개 일치 (%d원)- %d개"
        private const val MESSAGE_MATCH_COUNT_WITH_BONUS_BALL = "%d개 일치, 보너스 볼 일치(%d원)- %d개"
        private const val MESSAGE_PROFIT = "총 수익률은 %.2f입니다."
    }
}
