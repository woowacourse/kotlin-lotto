package lotto.view

import lotto.model.LottoPrize
import lotto.model.UserPrize

const val START_RESULT = "당첨 통계\n—--------"
const val YIELD_RESULT = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)"
fun winningStatistics() {
    println()
    println(START_RESULT)
}

fun outputWinningNumber(prize: UserPrize) {
    LottoPrize.entries.forEach {
        if (it != LottoPrize.BOOM) {
            println("${it.getMessage()} ${prize.getUserPrize().get(it) ?: 0}개")
        }
    }
}

fun calculationOfYield(prize: UserPrize, charge: Double) {
    println(YIELD_RESULT.format(prize.prizeRateCalculate(prize.prizeCalculate(), charge)))
}
