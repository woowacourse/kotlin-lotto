package lotto.domain

class WinningResult private constructor(val rankTable: Map<Rank, Int>) {

    fun sumTotalPrizeMoney(): Int =
        rankTable.keys.fold(0) { acc, rank -> acc + getPrizeMoney(rank) }

    private fun getPrizeMoney(rank: Rank): Int =
        rank.prizeMoney * (rankTable[rank] ?: throw IllegalStateException(INAPPROPRIATE_RANK_ERROR))

    companion object {
        private const val INAPPROPRIATE_RANK_ERROR = "올바르지 않은 rank 값이 포함되었습니다."

        fun from(ranks: List<Rank>): WinningResult {
            val value = Rank.values().map { rank -> rank to ranks.count { it == rank } }

            return WinningResult(value.toMap())
        }
    }
}
