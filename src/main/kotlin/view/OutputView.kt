package view

import domain.Lotto
import domain.Lottos
import domain.Money
import domain.Rank
import domain.WinningResult

class OutputView {
    fun outputMoneyMessage() {
        println(OUTPUT_MONEY_MESSAGE)
    }

    fun outputLottoSizeMessage(money: Money) {
        println((money.price / LOTTO_PRICE).toString() + OUTPUT_LOTTO_SIZE_MESSAGE)
    }

    fun outputLottos(lottos: Lottos) {
        lottos.lottos.forEach { lotto -> outputLotto(lotto) }
        println()
    }

    fun outputLotto(lotto: Lotto) {
        println(lotto.numbers.joinToString(prefix = PREFIX_MARK, separator = SEPERATOR_MARK, postfix = POSTFIX_MARK))
    }

    fun outputWinningLottoMessage() {
        println(OUTPUT_WINNING_LOTTO_MESSAGE)
    }

    fun outputBonusNumberMessage() {
        println(OUTPUT_BONUS_NUMBER_MESSAGE)
    }

    fun outputWinningResult(winningResult: WinningResult) {
        println(OUTPUT_WINNING_RESULT_MESSAGE)
        println(SEPERATE_MESSAGE)

        val rankResult = listOf(Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST)
        rankResult.forEach {
            println(it.toString() + winningResult.result[it] + "개")
        }
    }

    fun outputYield(yield: Double) {
        println(YIELD_MESSAGE.format(yield))
    }

    companion object {
        const val OUTPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요."
        const val OUTPUT_LOTTO_SIZE_MESSAGE = "개를 구매했습니다."
        const val PREFIX_MARK = "["
        const val SEPERATOR_MARK = ", "
        const val POSTFIX_MARK = "]"
        const val OUTPUT_WINNING_LOTTO_MESSAGE = "지난 주 당첨 번호를 입력해 주세요."
        const val OUTPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요."
        const val OUTPUT_WINNING_RESULT_MESSAGE = "당첨 통계"
        const val SEPERATE_MESSAGE = "---------"
        const val YIELD_MESSAGE = "총 수익률은 %.2f입니다."
        const val LOTTO_PRICE = 1000
    }
}
