package view

import domain.model.Lotto
import domain.model.LottoMatchResult
import domain.model.Rank
import util.joinToLineBreak
import kotlin.collections.sorted

class OutputView {
    fun printErrorMessage(message: String?) {
        println(message)
    }

    fun printPurchasedLottoCount(count: Int) {
        print(MESSAGE_PURCHASE_LOTTO_COUNT.format(count))
    }

    fun printPurchasedLotto(purchasedLotto: List<Lotto>) {
        val purchaseLottoMessage = makePurchaseLottoMessage(purchasedLotto)
        println(purchaseLottoMessage)
    }

    fun printWinningResult(
        winningResult: LottoMatchResult,
        profitRate: Double,
    ) {
        val roundedProfitRate = ROUND.format(profitRate)
        val lottoResultMessage = makeLottoResultMessage(winningResult)

        print(MESSAGE_WINNING_RESULT)
        println(lottoResultMessage)
        print(MESSAGE_TOTAL_PROFIT_RATE.format(roundedProfitRate))
    }

    fun printLossMessage() {
        println(MESSAGE_LOSS)
    }

    private fun makeLottoResultMessage(lottoResult: LottoMatchResult): String {
        return enumValues<Rank>().sortedBy {
            it.countOfMatch
        }
            .filterNot { rank ->
                rank == Rank.MISS
            }
            .map { rank ->
                val matchedCount = lottoResult.getWinningCount(rank)
                if (rank.requiresBonusMatch) {
                    MESSAGE_BONUS_BALL_MATCH.format(
                        rank.countOfMatch,
                        rank.winningMoney,
                        matchedCount,
                    )
                } else {
                    MESSAGE_EACH_RANK_RESULT.format(
                        rank.countOfMatch,
                        rank.winningMoney,
                        matchedCount,
                    )
                }
            }.joinToLineBreak()
    }

    private fun makePurchaseLottoMessage(purchaseLotto: List<Lotto>): String {
        return purchaseLotto
            .map {
                it.numbers.map {
                    it.value
                }
                    .sorted()
            }
            .joinToLineBreak()
    }

    companion object {
        const val MESSAGE_EACH_RANK_RESULT = "%d개 일치 (%d원)- %d개"
        const val MESSAGE_BONUS_BALL_MATCH = "%d개 일치, 보너스 볼 일치(%d원) - %d개"
        const val MESSAGE_PURCHASE_LOTTO_COUNT = "%d개를 구매했습니다.\n"
        const val MESSAGE_WINNING_RESULT = "당첨 통계\n---------\n"
        const val MESSAGE_LOSS = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)"
        const val MESSAGE_TOTAL_PROFIT_RATE = "총 수익률은 %s입니다."
        const val ROUND = "%.2f"
    }
}
