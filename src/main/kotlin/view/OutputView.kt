package view

import model.LottoResult
import model.LottoTicket
import model.Rank.FIFTH
import model.Rank.FIRST
import model.Rank.FOURTH
import model.Rank.SECOND
import model.Rank.THIRD

class OutputView {
    fun printLottoCount(lottoCount: Int) {
        println(PURCHASE_MESSAGE.format(lottoCount))
    }

    fun printLottoTickets(lottoTickets: List<LottoTicket>) {
        lottoTickets.forEach {
            println(it.lottoTicket.sorted())
        }
    }

    fun printWinningChart(lottoResult: LottoResult) {
        println(MATCH_MESSAGE.format(FIFTH.countOfMatch, FIFTH.winningMoney, lottoResult.getNum(FIFTH)))
        println(MATCH_MESSAGE.format(FOURTH.countOfMatch, FOURTH.winningMoney, lottoResult.getNum(FOURTH)))
        println(MATCH_MESSAGE.format(THIRD.countOfMatch, THIRD.winningMoney, lottoResult.getNum(THIRD)))
        println(MATCH_WITH_BONUS_MESSAGE.format(SECOND.countOfMatch, SECOND.winningMoney, lottoResult.getNum(SECOND)))
        println(MATCH_MESSAGE.format(FIRST.countOfMatch, FIRST.winningMoney, lottoResult.getNum(FIRST)))
    }

    fun printWinningRate(winningRate: Float) {
        println(TOTAL_WINNING_RATE_MESSAGE.format(winningRate))
    }

    companion object {
        const val PURCHASE_MESSAGE = "%d개를 구매했습니다."
        const val MATCH_MESSAGE = "%d개 일치 (%d원) - %d개"
        const val MATCH_WITH_BONUS_MESSAGE = "%d개 일치, 보너스 볼 일치 (%d원) - %d개"
        const val TOTAL_WINNING_RATE_MESSAGE = "총 수익률은 %.2f입니다."
    }
}
