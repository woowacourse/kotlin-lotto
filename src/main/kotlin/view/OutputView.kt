package view

import domain.Lotto
import domain.LottoCount
import domain.Rank
import domain.RankStatistics

class OutputView : OutputViewInterface {
    override fun printLottoCountResult(lottoCount: LottoCount) {
        println("수동으로 ${lottoCount.manualCount}장, 자동으로 ${lottoCount.automaticCount}개를 구매했습니다.")
    }

    override fun printAutomaticLottoNumbers(lotto: Lotto) {
        println(lotto.map { it.number })
    }

    override fun printAutomaticLotteries(lotteries: List<Lotto>) {
        lotteries.forEach { lotto -> printAutomaticLottoNumbers(lotto) }
    }

    override fun printRankStatistics(rankStatistics: RankStatistics) {
        println(RANK_STATISTICS_INTRO)
        Rank.values().forEach { rank: Rank ->
            printRankCount(rank, rankStatistics.getRankCount(rank))
        }
    }

    override fun printRankCount(rank: Rank, count: Int) {
        if (rank != Rank.MISS) println("${rank.countOfMatch}개 일치 (${rank.winningMoney}원) - $count 개)")
    }

    override fun printProfitRate(profitRate: Double, isLoss: Boolean) {
        println("총 수익률은 ${String.format("%.2f", profitRate)}입니다.")
        if (isLoss) println(PROFIT_RATE_LOSS)
        if (!isLoss) println(PROFIT_RATE_GAIN)
    }

    companion object {
        private const val RANK_STATISTICS_INTRO = "당첨 통계\n" +
            "---------"
        private const val PROFIT_RATE_LOSS = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)"
        private const val PROFIT_RATE_GAIN = "(기준이 1이기 때문에 결과적으로 이득이라는 의미임)"
    }
}
