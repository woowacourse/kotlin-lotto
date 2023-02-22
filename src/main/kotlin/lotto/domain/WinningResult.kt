package lotto.domain

class WinningResult private constructor(val value: Map<Rank, Int>) {

    companion object {
        fun from(ranks: List<Rank>): WinningResult {
            val value = Rank.values().map { rank -> rank to ranks.count { it == rank } }

            return WinningResult(value.toMap())
        }
    }
}
