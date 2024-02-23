package lotto.view

import lotto.model.Lotto
import lotto.model.LottoPrize
import lotto.model.UserPrize

const val PURCHASE_LOTTO_COUNT = "%d개를 구매했습니다."
const val START_RESULT = "당첨 통계\n—--------"
const val YIELD_RESULT = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)"

fun outputPurchaseCount(count: Int) {
    println(PURCHASE_LOTTO_COUNT.format(count))
}

fun outputLotto(lotto: Lotto) {
    println(lotto.getNumbers().sorted())
}

fun outputWinningStatistics() {
    println()
    println(START_RESULT)
}

fun outputWinningNumber(prize: UserPrize) {
    LottoPrize.entries.forEach {
        if (it != LottoPrize.BOOM) {
            println("${LottoPrize.getLottoMessage(it)} ${prize.getUserPrize().get(it) ?: 0}개")
        }
    }
}

fun outputCalculationOfYield(prize: UserPrize, charge: Double) {
    println(YIELD_RESULT.format(prize.prizeRateCalculate(prize.prizeCalculate(), charge)))
}
