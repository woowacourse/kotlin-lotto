package lotto.view

import lotto.constant.BonusResult
import lotto.constant.Rank
import lotto.domain.Lotto
import lotto.domain.LottoBunch
import lotto.domain.WinningResult

object OutputView {
    private const val WINNING_STATS_SCRIPT = "당첨 통계\n---------"
    private const val PURCHASE_COUNT_SCRIPT = "%d개를 구매했습니다."
    private const val YIELD_RATE_SCRIPT = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)"
    private const val RANK_SCRIPT_WITH_BONUS = "%d개 일치, 보너스 볼 일치(%d원)"
    private const val RANK_SCRIPT = "%d개 일치 (%d원)"
    private const val RANK_COUNT_SCRIPT = "- %d개"
    fun printPurchaseResult(lottoBunch: LottoBunch, purchaseCount: Int) {
        printPurchaseCount(purchaseCount)
        printLottoBunch(lottoBunch)
    }

    private fun printPurchaseCount(purchaseCount: Int) {
        println(PURCHASE_COUNT_SCRIPT.format(purchaseCount))
    }

    private fun printLottoBunch(lottoBunch: LottoBunch) {
        lottoBunch.value.forEach { lotto -> printLotto(lotto) }
        println()
    }

    private fun printLotto(lotto: Lotto) {
        println(lotto.lottoNumbers.map { it.value }.sorted().toString())
    }

    fun printWinningResult(winningResult: WinningResult) {
        println(WINNING_STATS_SCRIPT)
        val rankResult = winningResult.value.keys.drop(1)
        printRankResult(winningResult, rankResult)
    }

    private fun printRankResult(winningResult: WinningResult, rankResult: List<Rank>) {
        rankResult.forEach { rank ->
            winningResult.value[rank]?.let {
                println(formatRankResult(rank, it))
            }
        }
    }

    private fun formatRankResult(rank: Rank, winningCount: Int): String {
        if (rank.bonusResult == BonusResult.BONUS_MATCH) {
            return RANK_SCRIPT_WITH_BONUS.format(rank.matchCount, rank.prizeMoney) + RANK_COUNT_SCRIPT.format(
                winningCount,
            )
        }
        return RANK_SCRIPT.format(rank.matchCount, rank.prizeMoney) + RANK_COUNT_SCRIPT.format(winningCount)
    }

    fun printYieldRate(yieldRate: Double) {
        println(YIELD_RATE_SCRIPT.format(yieldRate))
    }
}
