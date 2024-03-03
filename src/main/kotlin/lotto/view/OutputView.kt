package lotto.view

import lotto.model.LottoStore
import lotto.model.WinningRank

class OutputView {
    fun printPurchaseAmountMessage() {
        println(MESSAGE_INPUT_PURCHASE_AMOUNT)
    }

    fun printManualLottoCountMessage() {
        println(MESSAGE_MANUAL_LOTTO_COUNT)
    }

    fun printManualLottoNumbersMessage() {
        println(MESSAGE_INPUT_MANUAL_LOTTO_NUMBERS)
    }

    fun printTotalLottoCountMessage(
        manualLottoCount: Int,
        autoLottoCount: Int,
    ) {
        println("\n수동으로 ${manualLottoCount}장, 자동으로 ${autoLottoCount}개를 구매했습니다.")
    }

    fun printLottoNumbers(lottoStore: LottoStore) {
        val lottoNumberStrings = lottoStore.showLottos()
        lottoNumberStrings.forEach { println(it) }
    }

    fun printWinningNumbersMessage() {
        println(MESSAGE_INPUT_WINNING_NUMBERS)
    }

    fun printBonusNumberMessage() {
        println(MESSAGE_INPUT_BONUS_NUMBER)
    }

    fun printWinningStatisticsMessage() {
        println(MESSAGE_WINNING_STATISTICS)
    }

    fun printRankStatistics(
        rank: WinningRank,
        count: Int,
    ) {
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
        private const val MESSAGE_INPUT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요."
        private const val MESSAGE_MANUAL_LOTTO_COUNT = "\n수동으로 구매할 로또 수를 입력해 주세요."
        private const val MESSAGE_INPUT_MANUAL_LOTTO_NUMBERS = "\n수동으로 구매할 번호를 입력해 주세요."
        private const val MESSAGE_INPUT_WINNING_NUMBERS = "\n지난 주 당첨 번호를 입력해 주세요."
        private const val MESSAGE_INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
        private const val MESSAGE_WINNING_STATISTICS = "당첨 통계\n---"
        private const val MESSAGE_PROFIT_RATE_START = "총 수익률은 "
        private const val MESSAGE_PROFIT_RATE_END = "입니다."
    }
}
