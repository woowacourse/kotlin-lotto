package view

import model.Quantity
import model.lottery.Lotteries
import model.lottery.Lottery
import model.profit.ProfitRate
import model.profit.ProfitStatus
import model.winning.WinningRank
import model.winning.WinningResult
import java.text.DecimalFormat

class OutputView {
    fun showLotteriesType(
        manualLotteryQuantity: Quantity,
        randomLotteryQuantity: Quantity,
    ) = println("수동으로 ${manualLotteryQuantity.count}개, 자동으로 ${randomLotteryQuantity.count}개 구매했습니다")

    fun showPurchasedLotteries(lotteries: Lotteries) {
        lotteries.lotteries.forEach { lottery ->
            showPurchasedLottery(lottery)
        }
        println()
    }

    private fun showPurchasedLottery(lottery: Lottery) = println("[${lottery.lotteryNumbers.joinToString(", ") { it.number.toString() }}]")

    fun showWinningResult(winningResult: WinningResult) {
        println(HEADER_WINNING_RESULT)

        for (result in winningResult.result.entries.reversed()) {
            when (result.key) {
                WinningRank.NONE -> {}
                else -> printMatchStatus(result)
            }
        }
    }

    private fun printMatchStatus(result: Map.Entry<WinningRank, Quantity>) =
        println(
            "${result.key.numbersMatchQuantity}개 일치 ${if (result.key.bonusNumberMatch) ", 보너스 볼 일치" else ""} ${
                MONEY_FORMAT.format(
                    result.key.winningPrize.amount.toInt(),
                )
            }- ${result.value.count}개",
        )

    fun showProfitRate(
        profitRate: ProfitRate,
        profitStatus: ProfitStatus,
    ) {
        print("총 수익률은 ${profitRate.rate}입니다.")
        println("(기준이 1이기 때문에 결과적으로 ${profitStatus.status}이라는 의미임)")
    }

    companion object {
        private val MONEY_FORMAT = DecimalFormat("(#,###원)")

        private val HEADER_WINNING_RESULT =
            """당첨 통계
            |---------
            """.trimMargin()
    }
}
