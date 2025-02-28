package lotto.domain.service

import lotto.Constants
import lotto.domain.model.LottoTicket
import lotto.domain.model.Rank
import lotto.domain.model.WinningLotto

class LottoResult(
    val ranks: Map<Rank, Int>,
) {
    companion object {
        fun calculateResult(
            lottoTickets: List<LottoTicket>,
            winningLotto: WinningLotto,
        ): LottoResult {
            val rankCounts =
                lottoTickets
                    .map { winningLotto.getRank(it) }
                    .groupingBy { it }
                    .eachCount()
            return LottoResult(rankCounts)
        }
    }

    fun calculateProfit(): Double {
        val totalWinningMoney =
            ranks.entries.sumOf { (key, value) ->
                key.winningMoney * value
            }
        return totalWinningMoney / (ranks.values.sum() * Constants.LOTTO_AMOUNT).toDouble()
    }
}
