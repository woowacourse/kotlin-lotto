package view

import model.PrizeCount
import model.WinningRank
import model.WinningResult
import model.lottery.Lotteries
import model.lottery.Lottery
import model.profit.ProfitRate
import model.profit.ProfitStatus
import java.text.DecimalFormat

class OutputView {
    fun showPurchasedLotteries(lotteries: Lotteries) {
        val amount = lotteries.lotteries.size

        println("${amount}개를 구매했습니다.")
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

    private fun printMatchStatus(result: Map.Entry<WinningRank, PrizeCount>) =
        println(
            "${result.key.matchNumbers}개 일치 ${if (result.key.bonusNumberMatch) ", 보너스 볼 일치" else ""} ${
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
