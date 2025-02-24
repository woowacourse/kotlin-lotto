package lotto.view

import lotto.domain.model.LottoNumber
import lotto.domain.model.LottoRank
import lotto.domain.model.LottoResult
import java.math.BigDecimal

class OutputView {
    fun printPurchaseLottoCount(purchaseLottoCount: Int) {
        println(PRINT_PURCHASE_LOTTO_COUNT_FORMAT.format(purchaseLottoCount))
    }

    fun printPurchaseLottoNumbers(lottoNumbers: List<LottoNumber>) {
        println(lottoNumbers.map { it.number })
    }

    fun printWinningResults(ranks: LottoResult) {
        println(PRINT_WINNING_RESULT_HEADER)
        LottoRank.entries.reversed().drop(DROP_MISS_RANK_INDEX).forEach { lottoRank ->
            printWinningResultFormat(lottoRank, ranks)
        }
    }

    private fun printWinningResultFormat(
        lottoRank: LottoRank,
        ranks: LottoResult,
    ) {
        val bonusComment = if (lottoRank == LottoRank.SECOND) BONUS_COMMENT else " "
        println(
            PRINT_WINNING_RESULT_FORMAT.format(
                lottoRank.matchCount,
                bonusComment,
                lottoRank.winningAmount,
                ranks.lottoRanks.count { it.name == lottoRank.name },
            ),
        )
    }

    fun printTotalReturns(totalReturns: BigDecimal) {
        val returnComment = if (totalReturns < LOSS_THRESHOLD.toBigDecimal()) RETURN_COMMENT else ""
        println(PRINT_TOTAL_RETURNS_FORMAT.format(totalReturns, returnComment))
    }

    private companion object {
        const val PRINT_WINNING_RESULT_HEADER = "\n당첨 통계\n---------"
        const val PRINT_PURCHASE_LOTTO_COUNT_FORMAT = "%s개 구매했습니다."
        const val PRINT_WINNING_RESULT_FORMAT = "%s개 일치%s(%s원) - %s개"
        const val PRINT_TOTAL_RETURNS_FORMAT = "총 수익률은 %s입니다.%s"
        const val RETURN_COMMENT = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)"
        const val BONUS_COMMENT = ", 보너스 볼 일치"
        const val DROP_MISS_RANK_INDEX = 1
        const val LOSS_THRESHOLD = 1
    }
}
