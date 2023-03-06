package presentation

import model.LottoResult
import model.domain.Rank
import model.domain.Rank.MISS

class GameStatisticsView {

    fun printLottoResult(lottoResult: LottoResult) {
        println(STATISTICS + BAR)
        Rank.values().filterNot { rank ->
            rank == MISS
        }.forEach { rank ->
            val resultCount = lottoResult.result[rank] ?: 0
            println(Lotto_Result_Format.format(rank.countOfMatch, rank.winningMoney, resultCount))
        }
    }

    fun printLottoProfitRate(profitRate: Double) =
        println(PROFIT_RATE.format(String.format(DOUBLE_TYPE_PROFITRATE, profitRate)))

    companion object {
        private const val PROFIT_RATE = "총 수익률은 %s입니다."
        private const val STATISTICS = "\n당첨 통계\n"
        private const val BAR = "---"
        private const val Lotto_Result_Format = "%d개 일치 (%s원) - %d개"
        private const val DOUBLE_TYPE_PROFITRATE = "%.2f"
    }
}
