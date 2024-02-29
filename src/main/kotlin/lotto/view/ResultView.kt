package lotto.view

import lotto.model.LottoPrize
import lotto.model.Lottos
import lotto.model.UserPrize
import lotto.util.Constant.SEPARATOR
import lotto.util.LottoMessage

const val START_RESULT = "당첨 통계\n—--------"
const val YIELD_RESULT = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)"
const val OPEN_BIG_BRACKET = "["
const val CLOSE_BIG_BRACKET = "]"

fun winningStatistics() {
    println()
    println(START_RESULT)
}

fun outputWinningNumber(prize: UserPrize) {
    LottoPrize.entries.forEach {
        if (it != LottoPrize.BOOM) {
            println("${getLottoMessage(it)} ${prize.getUserPrize()[it] ?: 0}개")
        }
    }
}

fun calculationOfYield(
    prize: UserPrize,
    charge: Double,
) {
    println(YIELD_RESULT.format(prize.prizeRateCalculate(prize.prizeCalculate(), charge)))
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

fun printCombinedLottos(combinedLottos: Lottos) {
    combinedLottos.getLottos().forEach { lotto ->
        val numbersIntList = lotto.numbers.map { it.lottoNumber }
        println(
            numbersIntList.sorted().joinToString(
                separator = SEPARATOR,
                prefix = OPEN_BIG_BRACKET,
                postfix = CLOSE_BIG_BRACKET,
            ),
        )
    }
}
