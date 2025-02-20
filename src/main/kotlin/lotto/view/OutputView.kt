package lotto.view

import lotto.domain.model.Lotto
import lotto.domain.model.LottoWinningStats
import lotto.domain.value.EarningRate
import lotto.enums.Rank

class OutputView {
    fun printPurchaseQuantity(purchaseQuantity: Int) {
        println("${purchaseQuantity}개를 구매했습니다.")
    }

    fun printLottos(lottos: List<Lotto>) {
        lottos.forEach {
            println(it.lottoNumbers.map { it.number })
        }
    }

    fun printLottoStats(lottoWinningStats: LottoWinningStats) {
        println("\n당첨 통계\n---------")
        lottoWinningStats.winningStats.entries.reversed().forEach { winningStats ->
            val bonusText = if (winningStats.key == Rank.SECOND) ", 보너스 볼 일치" else " "
            println("${winningStats.key.countOfMatch}개 일치$bonusText(${winningStats.key.winningMoney}원)- ${winningStats.value}개")
        }
    }

    fun printLottoEarningRate(earningRate: EarningRate) {
        println("총 수익률은 ${earningRate.rate}입니다.")
    }
}
