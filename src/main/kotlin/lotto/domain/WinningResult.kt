package lotto.domain

import lotto.constant.Rank

class WinningResult(val value: Map<Rank, Int>) {

    companion object {
        fun from(ranks: List<Rank>): WinningResult {
            val value = Rank.values().map { rank -> Pair(rank, ranks.count { it == rank }) }

            return WinningResult(value.toMap())
        }
    }
}
