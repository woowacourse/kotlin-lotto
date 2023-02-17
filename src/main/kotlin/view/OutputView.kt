package view

import domain.Rank
import domain.WinStatistics
import domain.lotto.LottoBundleDto
import domain.lotto.LottoNumber

object OutputView {
    fun printPurchasedLottoCount(lottoCount: Int) {
        println("${lottoCount}개를 구매했습니다.")
    }

    fun printPurchasedLotto(lottoBundleDto: LottoBundleDto) {
        lottoBundleDto.lottos.forEach {
            println(it.lottoNumbers.map { lottoNumber: LottoNumber -> lottoNumber.number })
        }
        println()
    }

    fun printWinStatistics(winStatistics: WinStatistics) {
        println()
        println("당첨 통계")
        println("---------")
        winStatistics.rankCount.toList().asReversed().forEach {
            printRankCount(it)
        }
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

    fun printEarningRate(earningRate: Double) {
        println("총 수익률은 ${earningRate}입니다.")
    }
}
