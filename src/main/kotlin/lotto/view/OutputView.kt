package lotto.view

import lotto.domain.model.Lotto
import lotto.domain.model.LottoResult
import lotto.domain.model.PurchaseDetail
import lotto.domain.value.EarningRate
import lotto.enums.Rank

class OutputView {
    fun printPurchaseDetail(purchaseDetail: PurchaseDetail) {
        val purchaseQuantity = purchaseDetail.purchaseAmount.getPurchaseQuantity()
        printPurchaseQuantity(purchaseQuantity)
        printLottos(purchaseDetail.lottos)
    }

    fun printLottoResult(lottoResult: LottoResult) {
        println("\n당첨 통계\n---------")
        lottoResult.winningStats.entries.reversed().forEach { winningStats ->
            val bonusText = if (winningStats.key == Rank.SECOND) ", 보너스 볼 일치" else " "
            println("${winningStats.key.countOfMatch}개 일치$bonusText(${winningStats.key.winningMoney}원)- ${winningStats.value}개")
        }
        printEarningRate(lottoResult.calculateEarningRate())
    }

    private fun printEarningRate(earningRate: EarningRate) {
        println("총 수익률은 ${earningRate.rate}입니다.")
    }

    private fun printPurchaseQuantity(purchaseQuantity: Int) {
        println("${purchaseQuantity}개를 구매했습니다.")
    }

    private fun printLottos(lottos: List<Lotto>) {
        lottos.forEach {
            println(it.lottoNumbers.map { it.number })
        }
    }
}
