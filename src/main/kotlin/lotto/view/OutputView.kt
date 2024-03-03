package lotto.view

import lotto.model.Lotto
import lotto.model.LottoPrize

private const val PURCHASE_LOTTO_COUNT = "수동으로 %d장, 자동으로 %d개를 구매했습니다."
private const val START_RESULT = "당첨 통계\n—--------"
private const val YIELD_RESULT = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)"
private const val LOTTO_BOOM = "꽝"
private const val LOTTO_FIFTH = "3개 일치 (5,000원) -"
private const val LOTTO_FOURTH = "4개 일치 (50,000원) -"
private const val LOTTO_THIRD = "5개 일치 (1,500,000원) -"
private const val LOTTO_SECOND = "5개 일치, 보너스 볼 일치 (30,000,000원) -"
private const val LOTTO_FIRST = "6개 일치 (2,000,000,000원) -"

fun outputNewLine() {
    println()
}

fun outputPurchaseCount(manualCount: Int, autoCount: Int) {
    println(PURCHASE_LOTTO_COUNT.format(manualCount, autoCount))
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
        LottoPrize.BOOM -> LOTTO_BOOM
        LottoPrize.FIFTH -> LOTTO_FIFTH
        LottoPrize.FOURTH -> LOTTO_FOURTH
        LottoPrize.THIRD -> LOTTO_THIRD
        LottoPrize.SECOND -> LOTTO_SECOND
        LottoPrize.FIRST -> LOTTO_FIRST
    }
}
