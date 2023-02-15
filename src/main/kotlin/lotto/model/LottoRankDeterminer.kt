package lotto.model

class LottoRankDeterminer(private val lotto: Lotto, private val winNumber: List<Int>, private val bonus: Int) :
    RankDeterminer {
    override fun determine(): Rank {
        val isBonus = lotto.numbers.contains(bonus)
        val countOfMatch = lotto.numbers.intersect(winNumber.toSet()).size

        if (isBonus && countOfMatch == 5)
            return Rank.SECOND
        return Rank.values().findLast { it.countOfMatch == countOfMatch } ?: Rank.MISS
    }
}
