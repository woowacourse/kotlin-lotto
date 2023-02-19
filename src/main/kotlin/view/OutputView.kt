package view

import domain.Lotto
import domain.Money
import domain.Rank
import domain.WinningResult

class OutputView {
    fun outputMoneyMessage() {
        println(OUTPUT_MONEY_MESSAGE)
    }

    fun outputLottoSizeMessage(money: Money) {
        println((money.getLottoCount()).toString() + OUTPUT_LOTTO_SIZE_MESSAGE)
    }

    fun outputLottos(lottos: List<Lotto>) {
        println(StringBuilder().getLottos(lottos))
    }

    private fun StringBuilder.getLottos(lottos: List<Lotto>): String {
        lottos.forEach {
            this.append(outputLotto(it) + "\n")
        }
        return this.toString()
    }
    fun outputErrorMessage(message: String) {
        println(message)
    }

    private fun outputLotto(lotto: Lotto): String {
        return lotto.numbers.map { it.number }.joinToString(prefix = PREFIX_MARK, separator = SEPERATOR_MARK, postfix = POSTFIX_MARK)
    }

    fun outputWinningLottoMessage() {
        println(OUTPUT_WINNING_LOTTO_MESSAGE)
    }

    fun outputBonusNumberMessage() {
        println(OUTPUT_BONUS_NUMBER_MESSAGE)
    }

    fun outputWinningResult(winningResult: WinningResult) {
        println("\n" + OUTPUT_WINNING_RESULT_MESSAGE)
        println(SEPERATE_MESSAGE)

        val rankResult = listOf(Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST)
        rankResult.forEach {
            println(OUTPUT_WINNING_RESULT.format(it.countOfMatch, it.winningMoney, if (winningResult.result.get(it) == null) 0 else winningResult.result.get(it)))
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
        const val OUTPUT_WINNING_RESULT = "%d개 일치 (%d원)- %d개 "
    }
}
