package view

import model.Lotteries
import model.Lottery
import model.ProfitRate
import model.ProfitStatus
import model.WinningRank
import model.WinningResult
import java.text.DecimalFormat

class OutputView {
    fun showPurchasedLotteries(lotteries: Lotteries) {
        val amount = lotteries.lotteries.size

        println("$amount" + GUIDE_PURCHASED)
        lotteries.lotteries.forEach { lottery ->
            showPurchasedLottery(lottery)
        }
        println()
    }

    private fun showPurchasedLottery(lottery: Lottery) =
        println(
            "$OPENING_SQUARE_BRACKET${
                lottery.lotteryNumbers.joinToString(
                    COMMA_WITH_SPACE,
                ) { it.number.toString() }
            }$CLOSING_SQUARE_BRACKET",
        )

    fun showWinningResult(winningResult: WinningResult) {
        println(HEADER_WINNING_RESULT)

        for (result in winningResult.result.entries.reversed()) {
            when (result.key) {
                WinningRank.NONE -> {}
                else -> printMatchStatus(result)
            }
        }
    }

    private fun printMatchStatus(result: Map.Entry<WinningRank, Int>) =
        println(
            "${result.key.matchNumbers}$MATCH_NUMBERS ${if (result.key.bonusNumberMatch) BONUS_BALL_MATCH else EMPTY_LINE} ${
                MONEY_FORMAT.format(
                    result.key.winningPrize.amount.toInt(),
                )
            }$DASH ${result.value}$COUNT",
        )

    fun showProfitRate(
        profitRate: ProfitRate,
        profitStatus: ProfitStatus,
    ) {
        print("$TOTAL_PROFIT_RATE ${profitRate.rate}$ENDING_MENTION")
        println("${OPENING_ROUND_BRACKET}$AS_RESULT_MENTION ${profitStatus.status}${MEANING_MENTION}$CLOSING_ROUND_BRACKET")
    }

    companion object {
        private val MONEY_FORMAT = DecimalFormat("(#,###원)")
        private const val GUIDE_PURCHASED = "개를 구매했습니다."
        private const val COMMA_WITH_SPACE = ", "
        private const val OPENING_SQUARE_BRACKET = "["
        private const val CLOSING_SQUARE_BRACKET = "]"
        private const val OPENING_ROUND_BRACKET = "([)"
        private const val CLOSING_ROUND_BRACKET = ")"
        private const val MATCH_NUMBERS = "개 일치"
        private const val BONUS_BALL_MATCH = ", 보너스 볼 일치"
        private const val EMPTY_LINE = ""
        private const val DASH = "-"
        private const val COUNT = "개"
        private const val TOTAL_PROFIT_RATE = "총 수익률은"
        private const val ENDING_MENTION = "입니다."
        private const val AS_RESULT_MENTION = "기준이 1이기 때문에 결과적으로"
        private const val MEANING_MENTION = "이라는 의미임"

        private val HEADER_WINNING_RESULT =
            """당첨 통계
            |---------
            """.trimMargin()
    }
}
