package view

import model.*
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

    private fun showPurchasedLottery(lottery: Lottery) =
        println("[${lottery.lotteryNumbers.joinToString(", ") { it.number.toString() }}]")

    fun showWinningResult(winningResult: WinningResult) {
        println(HEADER_WINNING_RESULT)

        winningResult.result.entries.reversed().forEach {
            println(
                "${it.key.matchNumbers}개 일치 ${
                    MONEY_FORMAT.format(
                        it.key.winningPrize.amount.toInt()
                    )
                }- ${it.value}개"
            )
        }
    }

    fun showProfitRate(profitRate: ProfitRate, profitStatus: ProfitStatus){
        print("총 수익률은 ${profitRate.rate}입니다.")
        println("(기준이 1이기 때문에 결과적으로 ${profitStatus.status}이라는 의미임)")
    }


    companion object {
        private val MONEY_FORMAT = DecimalFormat("(#,###원)")

        private val HEADER_WINNING_RESULT = """당첨 통계
            |---------""".trimMargin()
    }

}
