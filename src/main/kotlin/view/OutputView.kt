package view

import domain.Lotto
import domain.Lottos
import domain.Money
import domain.Rank
import domain.WinningResult

class OutputView {
    fun outputLottoSizeMessage(money: Money) {
        println((money.lottoCount(money.price)).toString() + OUTPUT_LOTTO_SIZE_MESSAGE)
    }

    fun outputLottos(lottos: Lottos) {
        lottos.lottos.forEach { lotto -> outputLotto(lotto) }
        println()
    }

    fun outputLotto(lotto: Lotto) {
        val lottoNumbers = lotto.numbers.map { it.getNumber() }
        println(lottoNumbers.joinToString(prefix = PREFIX_MARK, separator = SEPARATOR_MARK, postfix = POSTFIX_MARK))
    }

    fun outputWinningResult(winningResult: WinningResult) {
        println(OUTPUT_WINNING_RESULT_MESSAGE)
        println(SEPARATE_MESSAGE)

        val rankResult = listOf(Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST)
        rankResult.forEach {
            println(it.toString() + winningResult.result[it] + "개")
        }
    }

    fun outputYield(yield: Double) {
        println(YIELD_MESSAGE.format(yield))
    }

    companion object {
        const val OUTPUT_LOTTO_SIZE_MESSAGE = "개를 구매했습니다."
        const val PREFIX_MARK = "["
        const val SEPARATOR_MARK = ", "
        const val POSTFIX_MARK = "]"
        const val OUTPUT_WINNING_RESULT_MESSAGE = "당첨 통계"
        const val SEPARATE_MESSAGE = "---------"
        const val YIELD_MESSAGE = "총 수익률은 %.2f입니다."
    }
}
