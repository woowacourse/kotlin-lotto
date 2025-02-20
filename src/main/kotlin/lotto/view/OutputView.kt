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
                separator = LOTTO_NUMBER_SEPARATOR,
                prefix = LOTTO_NUMBERS_PREFIX,
                postfix = LOTTO_NUMBERS_POSTFIX,
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
        println(LOTTO_STATISTICS_TITLE)
        Rank.entries.reversed().drop(EXCLUDE_MISS_LANK).forEach { rank ->
            if (rank == Rank.SECOND) {
                printLottoSecondStatistics(lottoStatistics[rank] ?: INITIAL_LOTTO_RANK_COUNT)
            } else {
                println(
                    DEFAULT_RANK_STATISTICS_MESSAGE_FORMAT.format(
                        rank.matchCount,
                        rank.prizeMoney,
                        lottoStatistics[rank] ?: INITIAL_LOTTO_RANK_COUNT,
                    ),
                )
            }
        }
    }

    private fun printLottoSecondStatistics(lottoCount: Int) {
        println(
            SECOND_RANK_STATISTICS_MESSAGE_FORMAT.format(
                Rank.SECOND.matchCount,
                Rank.SECOND.prizeMoney,
                lottoCount,
            ),
        )
    }

    fun printLottoRateOfReturn(
        rateOfReturn: Double,
        isLossMoney: Boolean,
    ) {
        print(RATE_OF_RETURN_MESSAGE_FORMAT.format(rateOfReturn))
        if (isLossMoney) {
            print(RATE_OF_RETURN_IS_LOSS_MONEY_MESSAGE)
        }
    }

    companion object {
        private const val PURCHASE_AMOUNT_GUIDE_MESSAGE = "구입금액을 입력해 주세요."
        private const val LOTTO_COUNT_MESSAGE_FORMAT = "%d개를 구매했습니다."
        private const val LOTTO_NUMBER_SEPARATOR = ", "
        private const val LOTTO_NUMBERS_PREFIX = "["
        private const val LOTTO_NUMBERS_POSTFIX = "]"
        private const val WINNING_LOTTO_NUMBERS_GUIDE_MESSAGE = "지난 주 당첨 번호를 입력해 주세요."
        private const val BONUS_NUMBER_GUIDE_MESSAGE = "보너스 볼을 입력해 주세요."
        private const val ERROR_FORMAT = "[ERROR] %s"
        private const val LOTTO_STATISTICS_TITLE = "당첨 통계\n---------"
        private const val EXCLUDE_MISS_LANK = 1
        private const val INITIAL_LOTTO_RANK_COUNT = 0
        private const val DEFAULT_RANK_STATISTICS_MESSAGE_FORMAT = "%d개 일치 (%d원)- %d개"
        private const val SECOND_RANK_STATISTICS_MESSAGE_FORMAT = "%d개 일치, 보너스 볼 일치 (%d원) - %d개"
        private const val RATE_OF_RETURN_MESSAGE_FORMAT = "총 수익률을 %.2f입니다."
        private const val RATE_OF_RETURN_IS_LOSS_MONEY_MESSAGE = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)"
    }
}
