package lotto

import lotto.domain.Lotto
import lotto.global.Config.LOTTO_PRICE
import lotto.global.Config.MAX_LOTTO_LENGTH
import lotto.global.Config.MAX_RANDOM_NUM
import lotto.global.Config.MIN_RANDOM_NUM
import lotto.global.Rank
import kotlin.random.Random

class LottoService(
    private val random: Random,
) {
    fun getLotto(): Lotto {
        val lotto = mutableListOf<Int>()
        while (lotto.toSet().size != MAX_LOTTO_LENGTH) {
            lotto.clear()
            repeat(MAX_LOTTO_LENGTH) { lotto.add(random.nextInt(MIN_RANDOM_NUM, MAX_RANDOM_NUM + 1)) }
        }
        return Lotto(lotto.toList())
    }

    fun getManyLotto(iterates: Int): List<Lotto> {
        val manyLotto = mutableListOf<Lotto>()
        repeat(iterates) { manyLotto.add(getLotto()) }
        return manyLotto.toList()
    }

    fun checkRank(
        lotto: Lotto,
        winningLotto: Lotto,
        bonus: Int,
    ): Rank {
        val countOfMatch = winningLotto.value.count { it in lotto.value }
        val isBonusMatched = bonus in winningLotto.value && bonus !in lotto.value
        return Rank.getRank(countOfMatch, isBonusMatched)
    }

    fun checkRankMany(
        manyLotto: List<Lotto>,
        winningLotto: Lotto,
        bonus: Int,
    ): Map<Rank, Int> {
        val rankMap = Rank.entries.associateWith { 0 }.toMutableMap()
        for (lotto in manyLotto) {
            val rank = checkRank(lotto, winningLotto, bonus)
            rankMap[rank] = rankMap.getOrDefault(rank, 0) + 1
        }
        return rankMap.toMap()
    }

    companion object {
        fun getRate(rankMap: Map<Rank, Int>): String {
            val earned = rankMap.entries.sumOf { it.key.winningMoney * it.value }
            val paid = rankMap.values.sum() * LOTTO_PRICE
            return if (paid == 0) "0.0" else String.format("%.2f", earned.toDouble() / paid.toDouble())
        }
    }
}
