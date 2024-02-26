package view

import model.Lotto
import model.LottoGameResult
import model.Rank
import kotlin.math.floor

class ConsoleLottoGameOutputView : LottoGameOutputView {
    override fun showLottoCount(
        countOfAuto: Int,
        countOfManual: Int,
    ) {
        println(MESSAGE_PURCHASE_COUNT.format(countOfManual, countOfAuto))
    }

    override fun showPurchasedLottie(lottie: List<Lotto>) {
        println(MESSAGE_PURCHASED_LOTTIE_COUNT.format(lottie.size))
        lottie.forEach { println(it.numbers.map { it.number }) }
        println()
    }

    override fun showGameResult(
        results: List<LottoGameResult.RankResult>,
        rate: Double,
    ) {
        println(MESSAGE_TITLE)
        showRankState(results)
        showEarningRate(truncateDecimal(rate))
    }

    private fun showRankState(results: List<LottoGameResult.RankResult>) {
        val rankState =
            buildString {
                results.forEach { append(formatRankResult(it)) }
            }
        println(rankState)
    }

    private fun showEarningRate(rate: Double) {
        if (rate >= STANDARD_BREAK_EVEN_POINT) {
            println(MESSAGE_EARNING_RATE.format(rate))
        } else {
            println(MESSAGE_EARNING_RATE.format(rate) + MESSAGE_LOSS)
        }
    }

    private fun formatRankResult(rankResult: LottoGameResult.RankResult): String {
        val (rank, count) = rankResult
        return MESSAGE_RANK_RESULT_FORMAT.format(
            rank.countOfMatch,
            formatBonusMessage(rankResult),
            rank.winningMoney,
            count,
        )
    }

    private fun formatBonusMessage(rankResult: LottoGameResult.RankResult): String {
        return if (rankResult.rank == Rank.SECOND) MESSAGE_MATCH_BONUS_DESCRIPTION else EMPTY
    }

    private fun truncateDecimal(earningRate: Double): Double = floor(earningRate * SCALE) / SCALE

    companion object {
        private const val SCALE = 100
        private const val STANDARD_BREAK_EVEN_POINT = 1
        private const val MESSAGE_PURCHASE_COUNT = "수동으로 %d장, 자동으로 %d개를 구매했습니다."
        private const val MESSAGE_TITLE = "\n당첨 통계\n---------"
        private const val MESSAGE_PURCHASED_LOTTIE_COUNT = "%d개를 구매했습니다."
        private const val MESSAGE_EARNING_RATE = "총 수익률은 %.2f입니다."
        private const val MESSAGE_LOSS = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)"
        private const val MESSAGE_MATCH_BONUS_DESCRIPTION = "보너스 볼 일치"
        private const val EMPTY = ""
        private const val MESSAGE_RANK_RESULT_FORMAT = "%d개 일치, %s(%d원)- %d개\n"
    }
}
