package lotto.view

import lotto.model.Lottos
import lotto.model.WinningRank

class OutputView {
    fun printPurchaseAmountMessage() {
        println(MESSAGE_INPUT_PURCHASE_AMOUNT)
    }

    fun printManualLottoCountMessage() {
        println(MESSAGE_MANUAL_LOTTO_COUNT)
    }

    fun printNumberOfLottoMessage(numberOfLotto: Int) {
        println("${numberOfLotto}$MESSAGE_LOTTO_COUNT_PURCHASED")
    }

    fun printLottoNumbers(lottos: Lottos) {
        val lottoNumberStrings = lottos.printLottoNumbers()
        lottoNumberStrings.forEach { println(it) }
    }

    fun printWinningNumbersMessage() {
        println(MESSAGE_INPUT_WINNING_NUMBERS)
    }

    fun printBonusNumberMessage() {
        println(MESSAGE_INPUT_BONUS_NUMBER)
    }

    fun printRankStatistics(
        rank: WinningRank,
        count: Int,
    ) {
        println(MESSAGE_WINNING_STATISTICS)
        val formattedPrize = String.format("%,d원", rank.prize)
        if (rank == WinningRank.SECOND) {
            println("${rank.matchCount}개 일치, 보너스 볼 일치 $formattedPrize - ${count}개")
            return
        }
        println("${rank.matchCount}개 일치 $formattedPrize - ${count}개")
    }

    fun printProfitRateMessage(profitRate: Double) {
        println("$MESSAGE_PROFIT_RATE_START${String.format("%.2f", profitRate)}$MESSAGE_PROFIT_RATE_END")
    }

    companion object {
        private const val SEPARATOR_DELIMITER = ", "
        private const val PREFIX_DELIMITER = "["
        private const val POSTFIX_DELIMITER = "]"
        private const val MESSAGE_INPUT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요."
        private const val MESSAGE_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요."
        private const val MESSAGE_LOTTO_COUNT_PURCHASED = "개를 구매했습니다."
        private const val MESSAGE_INPUT_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요."
        private const val MESSAGE_INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
        private const val MESSAGE_WINNING_STATISTICS = "당첨 통계\n---"
        private const val MESSAGE_PROFIT_RATE_START = "총 수익률은 "
        private const val MESSAGE_PROFIT_RATE_END = "입니다."
    }
}
