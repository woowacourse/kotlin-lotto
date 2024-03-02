package lotto.model

class WinningTable(var winnings: Map<Rank, Int>) {
    init {
        val rankMap: MutableMap<Rank, Int> = Rank.entries.associateWith { 0 }.toMutableMap()
        Rank.entries.forEach {
            rankMap[it] = winnings[it] ?: 0
        }
        winnings = rankMap
    }
}
