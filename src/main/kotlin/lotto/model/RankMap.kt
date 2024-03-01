package lotto.model

@JvmInline
value class RankMap(val ranks: Map<Rank, Int>) {
    init {
        for (rank in Rank.entries) {
            require(ranks[rank] != null)
        }
    }
}
