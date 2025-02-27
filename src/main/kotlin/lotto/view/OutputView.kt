package lotto.view

import lotto.domain.model.LottoWinningStats
import lotto.domain.model.Lottos
import lotto.domain.value.EarningInfo
import lotto.domain.value.LottoPayInfo
import lotto.enums.GainLoss
import lotto.enums.GainLoss.GAIN
import lotto.enums.GainLoss.LOSS
import lotto.enums.GainLoss.PRINCIPAL
import lotto.enums.Rank

class OutputView {
    fun printLottoPurchaseQuantity(payInfo: LottoPayInfo) {
        println("\n수동으로 ${payInfo.manualLottoQuantity}장, 자동으로 ${payInfo.autoLottoQuantity}개를 구매했습니다.")
    }

    fun printTicketsByLottos(lottos: Lottos) {
        lottos.tickets.forEach { lotto ->
            println(
                lotto.getSortedLottoNumbers().map { it.number },
            )
        }
    }

    fun printLottoStats(lottoWinningStats: LottoWinningStats) {
        println("\n당첨 통계\n---------")
        lottoWinningStats.getWholeWinningStatsWithoutMiss().entries.reversed().forEach { winningStats ->
            val bonusText = if (winningStats.key == Rank.SECOND) ", 보너스 볼 일치" else " "
            println("${winningStats.key.countOfMatch}개 일치$bonusText(${winningStats.key.winningMoney}원)- ${winningStats.value}개")
        }
    }

    fun printLottoEarningRate(earningInfo: EarningInfo) {
        println("총 수익률은 ${earningInfo.rate}입니다.(기준이 1이기 때문에 결과적으로 ${convertGainLossToText(earningInfo.gainLoss)} 의미임)")
    }

    private fun convertGainLossToText(gainLoss: GainLoss): String =
        when (gainLoss) {
            GAIN -> "이득이라는"
            PRINCIPAL -> "본전이라는"
            LOSS -> "손해라는"
        }
}
