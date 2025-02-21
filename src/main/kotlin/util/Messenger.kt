package util

import domain.model.Lotto
import domain.model.Rank

object Messenger {
    const val EMPTY_VALUE = ""
    const val MESSAGE_EACH_RANK_RESULT = "%d개 일치 (%d원)- %d개"
    const val MESSAGE_BONUS_BALL_MATCH = "%d개 일치, 보너스 볼 일치(%d원) - %d개"

    fun makePurchaseLottoMessage(purchaseLotto: List<Lotto>): String {
        return purchaseLotto.map { it.numbers.map { it.value } }.joinToLineBreak()
    }

    fun getWinningMessage(rank: Map<Rank, Int>): String {
        return rank.map { (rank, matchedCount) ->
            when (rank) {
                Rank.MISS -> EMPTY_VALUE
                Rank.SECOND ->
                    MESSAGE_BONUS_BALL_MATCH.format(
                        rank.countOfMatch,
                        rank.winningMoney,
                        matchedCount,
                    )

                else ->
                    MESSAGE_EACH_RANK_RESULT.format(
                        rank.countOfMatch,
                        rank.winningMoney,
                        matchedCount,
                    )
            }
        }.joinToLineBreak()
    }
}
