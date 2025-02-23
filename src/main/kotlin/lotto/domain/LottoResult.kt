package lotto.domain

import lotto.util.Rank

class LottoResult(private val winningLotto: WinningLotto) {
    private var winningStats: Map<Rank, Int> = emptyMap()
    private var profitRate: Double = 0.0

    fun calculateWinningStats(lottoTickets: List<Lotto>) {
        val winningStats =
            mutableMapOf<Rank, Int>().apply {
                Rank.entries.filterNot { it == Rank.NONE }.forEach { this[it] = DEFAULT_VALUE }
                lottoTickets.forEach { updateWinningStats(winningLotto.match(it), this) }
            }
        this.winningStats = winningStats.toMap()
    }

    fun calculatePrize(): Long {
        var totalPrize: Long = DEFAULT_VALUE.toLong()

        winningStats.forEach { (state, count) ->
            totalPrize += state.price * count
        }
        return totalPrize
    }

    fun calculateProfit(
        totalPrize: Long,
        purchaseAmount: LottoPayment,
    ) {
        profitRate = totalPrize / purchaseAmount.toInt().toDouble()
    }

    fun getWinningStats() = winningStats

    fun getProfitRate() = profitRate

    private fun updateWinningStats(
        rankState: Rank,
        winningStats: MutableMap<Rank, Int>,
    ) {
        if (rankState == Rank.NONE) return
        winningStats[rankState] = (winningStats[rankState] ?: DEFAULT_VALUE) + INCREASED_VALUE
    }

    companion object {
        const val DEFAULT_VALUE = 0
        const val INCREASED_VALUE = 1
    }
}
