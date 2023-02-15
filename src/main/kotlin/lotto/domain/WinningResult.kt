package lotto.domain

class WinningResult(
    val countMatchRanks: MutableList<Int> = MutableList(6) { 0 }
) {
    fun countRank(rank: Rank) {
        when (rank) {
            Rank.FIRST -> countMatchRanks[0]++
            Rank.SECOND -> countMatchRanks[1]++
            Rank.THIRD -> countMatchRanks[2]++
            Rank.FOURTH -> countMatchRanks[3]++
            Rank.FIFTH -> countMatchRanks[4]++
            else -> countMatchRanks[5]++
        }
    }
}
