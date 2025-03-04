package lotto.domain.model.winning

import lotto.domain.valueobject.EarningRate
import lotto.domain.valueobject.LottoPaymentMoney
import lotto.domain.valueobject.PrizeMoney
import lotto.domain.valueobject.WinningQuantity

class WinningStatistics(
    val lottoPaymentMoney: LottoPaymentMoney,
    val winRankCounts: Map<Rank, WinningQuantity>,
) {
    fun getFullRanksWithoutMiss(): List<Pair<Rank, WinningQuantity>> {
        val emptyWinningStatus =
            Rank.entries
                .filter { it != Rank.MISS }
                .reversed()
                .map { it to WinningQuantity(0) }

        return emptyWinningStatus.map { (rank, quantity) ->
            rank to (winRankCounts[rank] ?: quantity)
        }
    }

    fun getTotalPrizeMoney(): PrizeMoney {
        val rawPrizeMoney: Long = winRankCounts.map { (rank, value) -> rank.winningMoney * value.quantity }.sumOf { it.toLong() }
        return PrizeMoney(rawPrizeMoney)
    }

    fun getEarningRate(): EarningRate = EarningRate(getTotalPrizeMoney().money.toDouble() / lottoPaymentMoney.money)
}
