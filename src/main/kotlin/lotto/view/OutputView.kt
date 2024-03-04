package lotto.view

import lotto.model.LottoCount
import lotto.model.LottoStore
import lotto.model.WinningRank

class OutputView {
    fun printError(message: String) {
        println("[ERROR] $message")
    }

    fun printTotalLottoCountMessage(lottoCount: LottoCount) {
        println("\n수동으로 ${lottoCount.manualLottoCount}장, 자동으로 ${lottoCount.autoLottoCount}개를 구매했습니다.")
    }

    fun printLottoNumbers(lottoStore: LottoStore) {
        val lottoNumberStrings = lottoStore.showLottos()
        lottoNumberStrings.forEach { println(it) }
    }

    fun printWinningResult(rankCounts: Map<WinningRank, Int>) {
        println("\n당첨 통계\n---")
        WinningRank.entries.forEach { rank ->
            if (rank != WinningRank.NONE) {
                printRankStatistics(rank, rankCounts[rank] ?: DEFAULT_COUNT)
            }
        }
    }

    private fun printRankStatistics(
        rank: WinningRank,
        count: Int,
    ) {
        val formattedPrize = String.format("%,d원", rank.prize)
        if (rank == WinningRank.SECOND) {
            println("${rank.matchCount}개 일치, 보너스 볼 일치 $formattedPrize - ${count}개")
            return
        }
        println("${rank.matchCount}개 일치 $formattedPrize - ${count}개")
    }

    fun printProfitRateMessage(profitRate: Double) {
        println("총 수익률은 ${String.format("%.2f", profitRate)}입니다.")
    }

    companion object {
        private const val DEFAULT_COUNT = 0
    }
}
