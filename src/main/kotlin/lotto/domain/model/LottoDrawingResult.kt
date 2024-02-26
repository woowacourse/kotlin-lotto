package lotto.domain.model

data class LottoDrawingResult(private val input: Map<Rank, Int>) {
    val statistics: Map<Rank, Int> = Rank.entries.associateWith { rank -> input.getOrDefault(rank, DEFAULT_COUNT) }

    companion object {
        fun from(ranks: List<Rank>) = LottoDrawingResult(ranks.groupingBy { rank -> rank }.eachCount())
        private const val DEFAULT_COUNT = 0
    }
}
