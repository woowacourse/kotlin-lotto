package lotto.view

import lotto.domain.model.Lotto
import lotto.domain.model.LottoWinningStats
import lotto.domain.value.PurchaseQuantity

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
        println("당첨 통계\n---------")
        lottoWinningStats.winningStats.entries.reversed().forEach { winningStats ->
            println("${winningStats.key.countOfMatch}개 일치 (${winningStats.key.winningMoney}원)- ${winningStats.value}개")
        }
    }
}
