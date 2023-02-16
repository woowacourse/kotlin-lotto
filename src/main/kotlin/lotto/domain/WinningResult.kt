package lotto.domain

import lotto.constant.Rank

class WinningResult(val map: Map<Rank, Int>) {

    override fun toString(): String {
        return map.keys.reversed().drop(1).joinToString(
            separator = WINNING_RESULT_TO_STRING_SEPARATOR,
        ) { EACH_RANK_SCRIPT.format(it.matchCount, it.prizeMoney, map[it]) }
    }

    companion object {
        private const val EACH_RANK_SCRIPT = "%d개 일치 (%d원)- %d개"
        private const val WINNING_RESULT_TO_STRING_SEPARATOR = "\n"

        fun from(ranks: List<Rank>): WinningResult {
            val value = Rank.values().map { rank -> Pair(rank, ranks.count { it == rank }) }

            return WinningResult(value.toMap())
        }
    }
}
