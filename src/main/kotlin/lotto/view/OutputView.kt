package lotto.view

import lotto.model.Lotto
import lotto.model.LottoPrize
import lotto.util.LottoMessage

const val PURCHASE_LOTTO_COUNT = "%d개를 구매했습니다."
const val START_RESULT = "당첨 통계\n—--------"
const val YIELD_RESULT = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)"

fun outputNewLine() {
    println()
}

fun outputPurchaseCount(count: Int) {
    println(PURCHASE_LOTTO_COUNT.format(count))
}

fun outputLottos(lottos: List<Lotto>) {
    lottos.forEach { lotto ->
        outputLotto(lotto)
    }
}

fun outputWinningStatistics() {
    println()
    println(START_RESULT)
}

fun outputWinningNumber(userMatches: Map<LottoPrize, Int>) {
    userMatches.forEach { (lottoPrize, count) ->
        if (lottoPrize != LottoPrize.BOOM) {
            println("${getLottoMessage(lottoPrize)} ${count}개")
        }
    }
}

fun outputCalculationOfYield(prizeCalculate: Double) {
    println(YIELD_RESULT.format(prizeCalculate))
}

private fun outputLotto(lotto: Lotto) {
    println(lotto.getLottoNumber())
}

private fun getLottoMessage(prize: LottoPrize): String {
    return when (prize) {
        LottoPrize.BOOM -> LottoMessage.BOOM
        LottoPrize.FIFTH -> LottoMessage.FIFTH
        LottoPrize.FOURTH -> LottoMessage.FOURTH
        LottoPrize.THIRD -> LottoMessage.THIRD
        LottoPrize.SECOND -> LottoMessage.SECOND
        LottoPrize.FIRST -> LottoMessage.FIRST
    }
}
