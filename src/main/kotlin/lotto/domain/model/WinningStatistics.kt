package lotto.domain.model

import lotto.domain.valueobject.EarningRate
import lotto.domain.valueobject.LottoPaymentMoney
import lotto.domain.valueobject.ObjectQuantity
import lotto.domain.valueobject.PrizeMoney

class WinningStatistics(
    val lottoPaymentMoney: LottoPaymentMoney,
    val winRankCounts: Map<Rank, ObjectQuantity>,
) {
    fun getFullRanksWithoutMiss(): List<Pair<Rank, ObjectQuantity>> {
        val emptyWinningStatus =
            Rank.entries
                .filter { it != Rank.MISS }
                .reversed()
                .map { it to ObjectQuantity(0) }

        return emptyWinningStatus.map { (rank, quantity) ->
            rank to (winRankCounts[rank] ?: quantity)
        }
    }

    fun getTotalPrizeMoney(): PrizeMoney {
        val rawPrizeMoney: Int = winRankCounts.map { (rank, value) -> rank.winningMoney * value.quantity }.sum()
        return PrizeMoney(rawPrizeMoney)
    }

    fun getEarningRate(): EarningRate = EarningRate(getTotalPrizeMoney().money.toDouble() / lottoPaymentMoney.money)
}
