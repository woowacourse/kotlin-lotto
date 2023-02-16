package lotto.domain

import lotto.constant.Rank

class WinningResult(private val map: Map<Rank, Int>) {

    override fun toString(): String {
        return map.keys.reversed().drop(1).joinToString(
            separator = WINNING_RESULT_TO_STRING_SEPARATOR,
        ) { findRankFormat(it) }
    }

    private fun findRankFormat(rank: Rank): String {
        if (rank == Rank.SECOND) {
            return SECOND_RANK_SCRIPT.format(rank.matchCount, rank.prizeMoney, map[rank])
        }
        return EACH_RANK_SCRIPT.format(rank.matchCount, rank.prizeMoney, map[rank])
    }

    companion object {
        private const val SECOND_RANK_SCRIPT = "%d개 일치, 보너스 볼 일치(%d원)- %d개"
        private const val EACH_RANK_SCRIPT = "%d개 일치 (%d원)- %d개"
        private const val WINNING_RESULT_TO_STRING_SEPARATOR = "\n"

        fun from(ranks: List<Rank>): WinningResult {
            val value = Rank.values().map { rank -> Pair(rank, ranks.count { it == rank }) }

            return WinningResult(value.toMap())
        }
    }
}
