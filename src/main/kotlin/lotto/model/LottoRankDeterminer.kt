package lotto.model

import lotto.entity.Lotto
import lotto.entity.WinLotto

class LottoRankDeterminer(private val lotto: Lotto, private val winLotto: WinLotto) :
    RankDeterminer {
    override fun determine(): Rank {
        val isBonus = lotto.numbers.contains(winLotto.bonus.value)
        val countOfMatch = lotto.numbers.intersect(winLotto.winNumber.value.toSet()).size

        if (isBonus && countOfMatch == 5)
            return Rank.SECOND
        return Rank.values().findLast { it.countOfMatch == countOfMatch } ?: Rank.MISS
    }
}
