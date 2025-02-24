package lotto.view

import lotto.domain.model.LottoWinningStats
import lotto.domain.model.Lottos
import lotto.domain.value.EarningInfo
import lotto.domain.value.LottoPayInfo
import lotto.enums.Rank

class OutputView {
    fun printLottoPurchaseQuantity(payInfo: LottoPayInfo) {
        println("${payInfo.getLottoPurchaseQuantity()}개를 구매했습니다.")
    }

    fun printTicketsByLottos(lottos: Lottos) {
        lottos.tickets.forEach { lotto ->
            println(
                lotto.getLottoNumbers().map { it.number },
            )
        }
    }

    fun printLottoStats(lottoWinningStats: LottoWinningStats) {
        println("\n당첨 통계\n---------")
        lottoWinningStats.getWinningStatsWithEmpty().entries.reversed().forEach { winningStats ->
            val bonusText = if (winningStats.key == Rank.SECOND) ", 보너스 볼 일치" else " "
            println("${winningStats.key.countOfMatch}개 일치$bonusText(${winningStats.key.winningMoney}원)- ${winningStats.value}개")
        }
    }

    fun printLottoEarningRate(earningInfo: EarningInfo) {
        println("총 수익률은 ${earningInfo.rate}입니다.(기준이 1이기 때문에 결과적으로 ${earningInfo.gainLoss.fullText} 의미임)")
    }
}
