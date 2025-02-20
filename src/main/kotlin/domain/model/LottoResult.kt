package domain.model

class LottoResult(
    val result: MutableMap<Rank, Int> =
        Rank.entries
            .reversed()
            .associateWith { 0 }
            .toMutableMap(),
)
