package view

import model.LottoResult
import model.LottoTicket
import model.Rank

class ConsoleOutputView : OutputView {
    override fun printLottoCount(lottoCount: Int) {
        println(PURCHASE_MESSAGE.format(lottoCount))
    }

    override fun printLottoTickets(lottoTickets: List<LottoTicket>) {
        lottoTickets.forEach {
            println(it.toIntList().sorted())
        }
    }

    override fun printWinningChart(lottoResult: LottoResult) {
        Rank.entries.forEach {
            val bonusMessage = if (it.isBonusNumberNecessary) BONUS_MESSAGE else ""
            println(MATCH_MESSAGE.format(it.countOfMatch, bonusMessage, it.winningMoney, lottoResult.getNum(it)))
        }
    }

    override fun printWinningRate(winningRate: Float) {
        println(TOTAL_WINNING_RATE_MESSAGE.format(winningRate))
    }

    companion object {
        const val PURCHASE_MESSAGE = "%d개를 구매했습니다."
        const val MATCH_MESSAGE = "%d개 일치%s (%d원) - %d개"
        const val BONUS_MESSAGE = ", 보너스 볼 일치"
        const val TOTAL_WINNING_RATE_MESSAGE = "총 수익률은 %.2f입니다."
    }
}
