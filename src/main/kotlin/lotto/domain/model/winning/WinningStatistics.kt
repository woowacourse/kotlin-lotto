package lotto.domain.model.winning

import lotto.domain.model.purchaseInfo.LottoPaymentMoney
import lotto.domain.model.result.EarningRate
import lotto.domain.model.result.PrizeMoney
import lotto.domain.model.result.WinningQuantity

class WinningStatistics(
    private val lottoPaymentMoney: LottoPaymentMoney,
    private val winRankCounts: Map<Rank, WinningQuantity>,
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
