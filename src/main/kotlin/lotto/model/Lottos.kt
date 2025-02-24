package lotto.model

class Lottos(
    val lottoBundle: List<Lotto>,
) {
    val size: Int
        get() = lottoBundle.size

    val ranks = mutableMapOf<Rank, Int>() // TODO: 외부에서도 접근이 가능하지 않을까?

    fun getRanks(winningLotto: WinningLotto) {
        lottoBundle.forEach { lotto ->
            val rank = winningLotto.findLottoRank(lotto)
            ranks[rank] = ranks.getOrDefault(rank, INITIAL_LOTTO_RANK_COUNT) + INCREMENT_COUNT_UNIT
        }
    }

    fun getTotalPrizeMoney(): Long = ranks.asIterable().sumOf { (rank, count) -> rank.prizeMoney * count }.toLong()

    companion object {
        private const val INITIAL_LOTTO_RANK_COUNT = 0
        private const val INCREMENT_COUNT_UNIT = 1
    }
}
