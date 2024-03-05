package lotto.view

import lotto.model.LottoResult
import lotto.model.LottoTicket
import lotto.model.Rank

class ConsoleOutputView : OutputView {
    override fun printLottoCount(
        manualCount: Int,
        autoCount: Int,
    ) {
        println(LOTTO_COUNT_MESSAGE.format(manualCount, autoCount))
    }

    override fun printLottoTickets(lottoTickets: List<LottoTicket>) {
        lottoTickets.forEach {
            println(it.lottoNumberSet)
        }
    }

    override fun printWinningChart(lottoResult: LottoResult) {
        Rank.entries.forEach {
            val bonusMessage = if (it.isBonusMatch == true) BONUS_MESSAGE else ""
            println(MATCH_MESSAGE.format(it.countOfMatch, bonusMessage, it.winningMoney, lottoResult.getNum(it)))
        }
    }

    override fun printWinningRate(winningRate: Float) {
        println(TOTAL_WINNING_RATE_MESSAGE.format(winningRate))
    }

    companion object {
        const val LOTTO_COUNT_MESSAGE = "수동으로 %d장, 자동으로 %d개를 구매했습니다."
        const val MATCH_MESSAGE = "%d개 일치%s (%s원) - %d개"
        const val BONUS_MESSAGE = ", 보너스 볼 일치"
        const val TOTAL_WINNING_RATE_MESSAGE = "총 수익률은 %.2f입니다."
    }
}
