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
}
