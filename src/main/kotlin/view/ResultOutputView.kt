package view

import domain.Purchaser
import domain.Rank
import domain.WinStatistics
import domain.lotto.LottoBundle
import domain.lotto.LottoNumber

object ResultOutputView {

    fun printResult(purchaser: Purchaser, winStatistics: WinStatistics) {
        printPurchasedLottoCount(purchaser.purchasedLottoCount)
        printPurchasedLotto(purchaser.purchasedLottoBundle)
        printWinStatistics(winStatistics)
    }

    private fun printPurchasedLottoCount(lottoCount: Int) {
        println("${lottoCount}개를 구매했습니다.")
    }

    private fun printPurchasedLotto(lottoBundle: LottoBundle) {
        lottoBundle.lottos.forEach {
            println(it.lottoNumbers.map { lottoNumber: LottoNumber -> lottoNumber.number })
        }
        println()
    }

    private fun printWinStatistics(winStatistics: WinStatistics) {
        println()
        println("당첨 통계")
        println("---------")
        winStatistics.rankCount.toList().asReversed().forEach {
            printRankCount(it)
        }
        printEarningRate(winStatistics.earningRate)
    }

    private fun printRankCount(rankCount: Pair<Rank, Int>) {
        if (rankCount.first == Rank.SECOND) {
            println("${rankCount.first.countOfMatch}개 일치, 보너스 볼 일치(${rankCount.first.winningMoney}원)- ${rankCount.second}개")
            return
        }
        if (rankCount.first == Rank.MISS) {
            return
        }
        println("${rankCount.first.countOfMatch}개 일치 (${rankCount.first.winningMoney}원)- ${rankCount.second}개")
    }

    private fun printEarningRate(earningRate: Double) {
        println("총 수익률은 ${earningRate}입니다.")
    }
}
