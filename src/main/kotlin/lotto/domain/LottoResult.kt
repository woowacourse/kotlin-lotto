package lotto.domain

class LottoResult(val result: Map<Rank, Int>) {
    companion object {
        fun of(lottoTickets: LottoTickets, winningLotto: WinningLotto): LottoResult {
            val result: MutableMap<Rank, Int> = lottoTickets.countOfRank(winningLotto).toMutableMap()
            for (rank in Rank.values()) {
                result.putIfAbsent(rank, 0)
            }
            return LottoResult(result.toSortedMap(reverseOrder()))
        }
    }

    fun calculateProfit(purchaseAmount: PurchaseAmount): Double {
        val sum = result.entries
            .map { it.key.prize * it.value }
            .sum().toDouble()

        return purchaseAmount.calculateProfit(sum)
    }
}
