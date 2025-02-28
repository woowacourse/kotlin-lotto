package view

import domain.model.LottoMatchResult
import domain.model.Rank
import domain.model.lotto.Lotto
import util.joinToLineBreak
import kotlin.collections.sorted

class OutputView {
    fun printErrorMessage(message: String?) {
        println(message)
    }

    fun printManualLottoRequest() {
        println(MESSAGE_INPUT_PASSIVITY_LOTTO_NUMBERS)
    }

    fun printPurchasedLottoAmount(
        passivityLottoAmount: Int,
        autoLottoAmount: Int,
    ) {
        println(MESSAGE_BOUGHT_LOTTO_RESULT.format(passivityLottoAmount, autoLottoAmount))
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
        return enumValues<Rank>()
            .sortedBy {
                it.countOfMatch
            }
            .filterNot { rank ->
                rank == Rank.MISS
            }
            .map { rank ->
                val matchedCount = lottoResult.getWinningCount(rank)
                getMatchedResultMessage(rank, matchedCount)
            }
            .joinToLineBreak()
    }

    private fun getMatchedResultMessage(
        rank: Rank,
        matchedCount: Int,
    ): String {
        return if (rank.requiresBonusMatch) {
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
        private const val MESSAGE_INPUT_PASSIVITY_LOTTO_NUMBERS = "수동으로 구매할 번호를 입력해 주세요."
        private const val MESSAGE_BOUGHT_LOTTO_RESULT = "\n수동으로 %d장, 자동으로 %d개를 구매했습니다."
        const val MESSAGE_EACH_RANK_RESULT = "%d개 일치 (%d원)- %d개"
        const val MESSAGE_BONUS_BALL_MATCH = "%d개 일치, 보너스 볼 일치(%d원) - %d개"
        const val MESSAGE_WINNING_RESULT = "당첨 통계\n---------\n"
        const val MESSAGE_LOSS = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)"
        const val MESSAGE_TOTAL_PROFIT_RATE = "총 수익률은 %s%%입니다."
        const val ROUND = "%.2f"
    }
}
