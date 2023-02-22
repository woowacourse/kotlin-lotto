package view

import domain.Lotto
import domain.LottoCount
import domain.Rank
import domain.RankStatistics

interface OutputViewInterface {
    fun printLottoCountResult(lottoCount: LottoCount)
    fun printAutomaticLottoNumbers(lotto: Lotto)
    fun printAutomaticLotteries(lotteries: List<Lotto>)
    fun printRankStatistics(rankStatistics: RankStatistics)

    fun printRankCount(rank: Rank, count: Int)
    fun printProfitRate(profitRate: Double, isLoss: Boolean)
}
