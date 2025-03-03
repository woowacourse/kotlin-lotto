package lotto.view

import lotto.domain.model.lottoticket.LottoTicket
import lotto.domain.model.winning.GainLoss
import lotto.domain.model.winning.Rank
import lotto.domain.model.winning.WinningStatistics
import lotto.domain.valueobject.EarningRate
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

    fun showEarningRate(earningRates: EarningRate) {
        println(BODY_EARNING_RATE_INFO.format(earningRates.rate, getGainLossText(earningRates)))
    }

    companion object {
        private const val ALERT_BOUGHT_LOTTO_QUANTITY = "수동으로 %d장, 자동으로 %d개를 구매했습니다."
        private const val HEADER_WINNING_STATICS = "당첨 통계\n---------"
        private const val BODY_SINGLE_LINE_WINNING_STATICS = "%d개 일치%s(%d원)- %d개"
        private const val BODY_EARNING_RATE_INFO = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 %s 의미임)"

        private fun getBonusBallText(rank: Rank): String =
            when (rank) {
                Rank.SECOND -> ", 보너스 볼 일치"
                else -> " "
            }

        private fun getGainLossText(earningRates: EarningRate): String =
            when (GainLoss.valueOf(earningRates.rate)) {
                GainLoss.GAIN -> "이득이라는"
                GainLoss.LOSS -> "손해라는"
                else -> "본전이라는"
            }
    }
}
