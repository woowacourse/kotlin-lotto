package lotto.domain

import lotto.constant.Rank

class WinningResult(val value: HashMap<Rank, Int>) {
    fun getYieldRate(purchaseMoney: PurchaseMoney): Double =
        getTotalPrizeMoney() / purchaseMoney.value.toDouble()

    private fun getTotalPrizeMoney(): Int =
        value.keys.sumOf { rank -> rank.prizeMoney * value[rank]!! }

    companion object {
        fun from(ranks: List<Rank>): WinningResult {
            val value = Rank.values().map { rank -> Pair(rank, ranks.count { it == rank }) }
            return WinningResult(value.toMap() as HashMap<Rank, Int>)
        }
    }
}
