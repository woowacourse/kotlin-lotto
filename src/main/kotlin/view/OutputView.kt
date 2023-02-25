package view

import domain.Lotto
import domain.Rank
import domain.WinningResult

object OutputView {
    private const val OUTPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요."
    private const val OUTPUT_LOTTO_SIZE_MESSAGE = "수동으로 %d장, 자동으로 %d개를 구매했습니다."
    private const val PREFIX_MARK = "["
    private const val SEPERATOR_MARK = ", "
    private const val POSTFIX_MARK = "]"
    private const val OUTPUT_WINNING_LOTTO_MESSAGE = "지난 주 당첨 번호를 입력해 주세요."
    private const val OUTPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요."
    private const val OUTPUT_MANUAL_LOTTO_COUNT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요."
    private const val OUTPUT_MANUAL_LOTTOS_MESSAGE = "수동으로 구매할 번호를 입력해 주세요."
    private const val OUTPUT_WINNING_RESULT_MESSAGE = "당첨 통계"
    private const val SEPERATE_MESSAGE = "---------"
    private const val YIELD_MESSAGE = "총 수익률은 %.2f입니다."
    private const val OUTPUT_WINNING_RESULT = "%d개 일치 (%d원)- %d개 "
    fun outputMoneyMessage() {
        println(OUTPUT_MONEY_MESSAGE)
    }

    fun outputLottoSizeMessage(manualLottoCount: Int, autoLottoCount: Int) {
        println(OUTPUT_LOTTO_SIZE_MESSAGE.format(manualLottoCount, autoLottoCount))
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
        return lotto.map { it.number }
            .joinToString(prefix = PREFIX_MARK, separator = SEPERATOR_MARK, postfix = POSTFIX_MARK)
    }

    fun outputWinningLottoMessage() {
        println(OUTPUT_WINNING_LOTTO_MESSAGE)
    }

    fun outputBonusNumberMessage() {
        println(OUTPUT_BONUS_NUMBER_MESSAGE)
    }

    fun outputManualLottoCountMessage() {
        println(OUTPUT_MANUAL_LOTTO_COUNT_MESSAGE)
    }
    fun outputManualLottosMessage() {
        println(OUTPUT_MANUAL_LOTTOS_MESSAGE)
    }

    fun outputWinningResult(winningResult: WinningResult) {
        println("\n" + OUTPUT_WINNING_RESULT_MESSAGE)
        println(SEPERATE_MESSAGE)

        val rankResult = listOf(Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST)
        rankResult.forEach {
            println(
                OUTPUT_WINNING_RESULT.format(
                    it.countOfMatch,
                    it.winningMoney,
                    if (winningResult.result.get(it) == null) 0 else winningResult.result.get(it),
                ),
            )
        }
    }

    fun outputYield(yield: Double) {
        println(YIELD_MESSAGE.format(yield))
    }
}
