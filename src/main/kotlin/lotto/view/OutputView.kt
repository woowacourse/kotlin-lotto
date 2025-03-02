package lotto.view

import lotto.domain.model.LottoTicket
import lotto.domain.model.Rank
import lotto.domain.model.WinningStatistics
import lotto.domain.valueobject.ObjectQuantity

class OutputView {
    fun showParagraphSeparation() {
        println()
    }

    fun showBoughtLottoQuantity(
        manualQuantity: ObjectQuantity,
        autoQuantity: ObjectQuantity,
    ) {
        println(ALERT_BOUGHT_LOTTO_QUANTITY.format(manualQuantity.quantity, autoQuantity.quantity))
    }

    fun showBoughtLottoTickets(lottoTickets: List<LottoTicket>) {
        lottoTickets.forEach { ticket ->
            println(ticket.getSortedLottoNumbers())
        }
        showParagraphSeparation()
    }

    fun showWinningStatics(winningStatics: WinningStatistics) {
        println(HEADER_WINNING_STATICS)
        winningStatics.getFullRanksWithoutMiss().forEach { (rank, matchCount) ->
            println(
                BODY_SINGLE_LINE_WINNING_STATICS.format(
                    rank.countOfMatch,
                    getBonusBallText(rank),
                    rank.winningMoney,
                    matchCount.quantity,
                ),
            )
        }
    }

    companion object {
        private const val ALERT_BOUGHT_LOTTO_QUANTITY = "수동으로 %d장, 자동으로 %d개를 구매했습니다."
        private const val HEADER_WINNING_STATICS = "당첨 통계\n---------"
        private const val BODY_SINGLE_LINE_WINNING_STATICS = "%d개 일치%s(%d원)- %d개"

        private fun getBonusBallText(rank: Rank): String =
            when (rank) {
                Rank.SECOND -> ", 보너스 볼 일치"
                else -> " "
            }
    }
}
