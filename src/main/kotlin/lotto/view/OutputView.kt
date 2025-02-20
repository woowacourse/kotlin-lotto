package lotto.view

import lotto.domain.model.Lotto
import lotto.domain.model.LottoWinningStats
import lotto.domain.value.EarningRate
import lotto.domain.value.PurchaseQuantity
import lotto.enums.Rank

class OutputView {
    fun printPurchaseQuantity(purchaseQuantity: PurchaseQuantity) {
        println("${purchaseQuantity.quantity}개를 구매했습니다.")
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
        val gainLossText = if (earningRate.rate > 1) "이득이라는" else (if (earningRate.rate == 1.0) "본전이라는" else "손해라는")
        println("총 수익률은 ${earningRate.rate}입니다.(기준이 1이기 때문에 결과적으로 $gainLossText 의미임)")
    }
}
