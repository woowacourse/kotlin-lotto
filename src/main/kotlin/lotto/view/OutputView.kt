package lotto.view

import lotto.model.Lotto
import lotto.model.Rank

class OutputView {
    fun printPurchaseAmountGuide() {
        println(PURCHASE_AMOUNT_GUIDE_MESSAGE)
    }

    fun printLottoCount(lottoCount: Int) {
        println(LOTTO_COUNT_MESSAGE_FORMAT.format(lottoCount))
    }

    fun printLottoNumbers(lotto: Lotto) {
        println(
            lotto.numbers.joinToString(
                separator = ", ",
                prefix = "[",
                postfix = "]",
            ) { lottoNumber -> lottoNumber.number.toString() },
        )
    }

    fun printWinningLottoNumbersGuide() {
        println(WINNING_LOTTO_NUMBERS_GUIDE_MESSAGE)
    }

    fun printBonusNumberGuide() {
        println(BONUS_NUMBER_GUIDE_MESSAGE)
    }

    fun printErrorMessage(message: String) {
        println(ERROR_FORMAT.format(message))
    }

    fun printLottoStatistics(lottoStatistics: Map<Rank, Int>) {
        println("당첨 통계")
        println("---------")
        Rank.entries.reversed().drop(1).forEach { rank ->
            if (rank == Rank.SECOND) {
                printLottoSecondStatistics(lottoStatistics[rank] ?: 0)
            } else {
                println("${rank.matchCount}개 일치 (${rank.prizeMoney}원)- ${lottoStatistics[rank] ?: 0}개")
            }
        }
    }

    private fun printLottoSecondStatistics(lottoCount: Int) {
        println("${Rank.SECOND.matchCount}개 일치, 보너스볼 일치(${Rank.SECOND.prizeMoney}원) - ${lottoCount}개")
    }

    companion object {
        private const val PURCHASE_AMOUNT_GUIDE_MESSAGE = "구입금액을 입력해 주세요."
        private const val LOTTO_COUNT_MESSAGE_FORMAT = "%d개를 구매했습니다."
        private const val WINNING_LOTTO_NUMBERS_GUIDE_MESSAGE = "지난 주 당첨 번호를 입력해 주세요."
        private const val BONUS_NUMBER_GUIDE_MESSAGE = "보너스 볼을 입력해 주세요."
        private const val ERROR_FORMAT = "[ERROR] %s"
    }
}
