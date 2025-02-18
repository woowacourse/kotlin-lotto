package lotto.view

import lotto.domain.model.LottoRank

class OutputView {
    fun printPurchaseLottoCount(purchaseLottoCount: Int) {
        println(PRINT_PURCHASE_LOTTO_COUNT_FORMAT.format(purchaseLottoCount))
    }

    fun printPurchaseLottoNumbers(lottoNumbers: List<Int>) {
        println(lottoNumbers)
    }

    fun printWinningResults(ranks: List<LottoRank>) {
        println(PRINT_WINNING_RESULT_HEADER)
        LottoRank.entries.reversed().drop(1).forEach { lottoRank ->
            printWinningResultFormat(lottoRank, ranks)
        }
    }

    private fun printWinningResultFormat(
        lottoRank: LottoRank,
        ranks: List<LottoRank>,
    ) {
        val bonusComment = if (lottoRank == LottoRank.SECOND) ", 보너스 볼 일치" else " "
        println(
            PRINT_WINNING_RESULT_FORMAT.format(
                lottoRank.matchCount,
                bonusComment,
                lottoRank.winningAmount,
                ranks.count { it.name == lottoRank.name },
            ),
        )
    }

    private companion object {
        const val PRINT_WINNING_RESULT_HEADER = "당첨 통계\n---------"
        const val PRINT_PURCHASE_LOTTO_COUNT_FORMAT = "%s개 구매했습니다."
        const val PRINT_WINNING_RESULT_FORMAT = "%s개 일치%s(%s원- %s개)"
    }
}
