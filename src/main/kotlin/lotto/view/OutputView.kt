package lotto.view

import lotto.domain.model.LottoRank
import lotto.domain.model.LottoStore
import java.math.BigDecimal

class OutputView {
    fun printPurchaseLottoCount(
        manualLottoCount: Int,
        randomLottoCount: Int,
    ) {
        println(PRINT_PURCHASE_LOTTO_COUNT_FORMAT.format(manualLottoCount, randomLottoCount))
    }

    fun printPurchaseLottoNumbers(lottoNumbers: List<Int>) {
        println(lottoNumbers)
    }

    fun printManualLottoNumbers() {
        println(PRINT_MANUAL_LOTTO_NUMBERS_MESSAGE)
    }

    fun printWinningNumbers() {
        println(PRINT_WINNING_NUMBERS_MESSAGE)
    }

    fun printBonusNumber() {
        println(READ_BONUS_NUMBER_MESSAGE)
    }

    fun printWinningResults(ranks: LottoStore) {
        println(PRINT_WINNING_RESULT_HEADER)
        ranks.lottoRanks.keys.sortedBy { it.winningAmount }.drop(DROP_MISS_RANK_INDEX).forEach { lottoRank ->
            printWinningResultFormat(lottoRank, ranks)
        }
    }

    private fun printWinningResultFormat(
        lottoRank: LottoRank,
        ranks: LottoStore,
    ) {
        val bonusComment = if (lottoRank == LottoRank.SECOND) BONUS_COMMENT else " "
        println(
            PRINT_WINNING_RESULT_FORMAT.format(
                lottoRank.matchCount,
                bonusComment,
                lottoRank.winningAmount,
                ranks.lottoRanks[lottoRank] ?: 0,
            ),
        )
    }

    fun printTotalReturns(totalReturns: BigDecimal) {
        val returnComment = if (totalReturns < LOSS_THRESHOLD.toBigDecimal()) RETURN_COMMENT else ""
        println(PRINT_TOTAL_RETURNS_FORMAT.format(totalReturns, returnComment))
    }

    fun printErrorMessage(message: String) {
        println(message)
    }

    private companion object {
        const val PRINT_MANUAL_LOTTO_NUMBERS_MESSAGE = "수동으로 구매할 번호를 입력해 주세요."
        const val PRINT_WINNING_NUMBERS_MESSAGE = "\n지난 주 당첨 번호를 입력해 주세요."
        const val READ_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요."
        const val PRINT_WINNING_RESULT_HEADER = "\n당첨 통계\n---------"
        const val PRINT_PURCHASE_LOTTO_COUNT_FORMAT = "수동으로 %d장, 자동으로 %d개를 구매했습니다."
        const val PRINT_WINNING_RESULT_FORMAT = "%s개 일치%s(%s원) - %s개"
        const val PRINT_TOTAL_RETURNS_FORMAT = "총 수익률은 %s입니다.%s"
        const val RETURN_COMMENT = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)"
        const val BONUS_COMMENT = ", 보너스 볼 일치"
        const val DROP_MISS_RANK_INDEX = 1
        const val LOSS_THRESHOLD = 1
    }
}
