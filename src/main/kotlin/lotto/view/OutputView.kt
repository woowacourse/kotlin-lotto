package lotto.view

import lotto.domain.PurchaseAmount
import lotto.domain.Rank
import lotto.domain.WinningResult

class OutputView {
    fun printWinningStats(amount: PurchaseAmount, result: WinningResult) {
        println(NOTICE_MESSAGE_WINNING_STAT)
        println(DIVIDER_LINE)
        println("${Rank.FIFTH.description} (${Rank.FIFTH.winningMoney})- ${result.countMatchRanks[Rank.FIFTH.ordinal]}")
        println("${Rank.FOURTH.description} (${Rank.FOURTH.winningMoney})- ${result.countMatchRanks[Rank.FOURTH.ordinal]}")
        println("${Rank.THIRD.description} (${Rank.THIRD.winningMoney})- ${result.countMatchRanks[Rank.THIRD.ordinal]}")
        println("${Rank.SECOND.description} (${Rank.SECOND.winningMoney})- ${result.countMatchRanks[Rank.SECOND.ordinal]}")
        println("${Rank.FIRST.description} (${Rank.FIRST.winningMoney})- ${result.countMatchRanks[Rank.FIRST.ordinal]}")
        println(NOTICE_FORMAT_MESSAGE_TOTAL_YIELD.format(result.calculateYield(amount.amount)))
    }

    companion object {
        private const val DIVIDER_LINE = "---------"

        private const val NOTICE_MESSAGE_WINNING_STAT = "당첨 통계"

        private const val NOTICE_FORMAT_MESSAGE_TOTAL_YIELD = "총 수익률은 %.2f입니다."
    }
}
