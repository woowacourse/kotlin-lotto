package view

import model.Count
import model.Lotteries
import model.Lottery
import model.ProfitRate
import model.ProfitStatus
import model.WinningRank
import model.WinningResult
import java.text.DecimalFormat

class OutputView {
    fun showManualLottoInputGuide() = println(GUIDE_INPUT_MANUAL_LOTTERIES)

    fun showPurchasedLotteries(
        lotteries: Lotteries,
        count: Count,
    ) {
        val manual = count.amount
        val auto = lotteries.lotteries.size - manual

        println(GUIDE_PURCHASED.format(manual, auto))
        lotteries.lotteries.forEach { lottery ->
            showPurchasedLottery(lottery)
        }
        println()
    }

    private fun showPurchasedLottery(lottery: Lottery) =
        println(
            PRINT_LOTTERY_NUMBER_FORMAT
                .format(
                    lottery.lotteryNumbers.joinToString(
                        COMMA_WITH_SPACE,
                    ) { it.number.toString() },
                ),
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
            "${MATCH_NUMBERS.format(result.key.matchNumbers)}${
                if (result.key.bonusNumberMatch) BONUS_BALL_MATCH else EMPTY_LINE
            } ${
                MONEY_FORMAT.format(
                    result.key.winningPrize.amount.toInt(),
                )
            }$DASH ${result.value}$COUNT",
        )

    fun showProfitRate(
        profitRate: ProfitRate,
        profitStatus: ProfitStatus,
    ) {
        println(PROFIT_RESULT_ANNOUNCEMENT.format(profitRate.rate, profitStatus.status))
    }

    companion object {
        private const val GUIDE_INPUT_MANUAL_LOTTERIES = "\n수동으로 구매할 번호를 입력해 주세요."
        private val MONEY_FORMAT = DecimalFormat("(#,###원)")
        private const val GUIDE_PURCHASED = "\n수동으로 %d장, 자동으로 %d개를 구매했습니다."
        private const val COMMA_WITH_SPACE = ", "
        private const val PRINT_LOTTERY_NUMBER_FORMAT = "[%s]"
        private const val MATCH_NUMBERS = "%d개 일치"
        private const val BONUS_BALL_MATCH = ", 보너스 볼 일치"
        private const val EMPTY_LINE = ""
        private const val DASH = "-"
        private const val COUNT = "개"
        private const val PROFIT_RESULT_ANNOUNCEMENT = "총 수익률은 %.1f입니다.(기준이 1이기 때문에 결과적으로 %s이라는 의미임)"

        private const val HEADER_WINNING_RESULT = "\n당첨 통계\n---------"
    }
}
