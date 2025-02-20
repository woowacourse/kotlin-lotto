package lotto

import lotto.domain.Lotto
import lotto.global.Rank
import kotlin.random.Random

class LottoService(private val random: Random) {
    fun getLotto(): Lotto {
        val lotto = mutableListOf<Int>()
        while (lotto.toSet().size != 6) {
            lotto.clear()
            repeat(6) { lotto.add(random.nextInt(1, 46)) }
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
        var countOfMatch = 0
        lotto.value.forEach { num -> if (num in winningLotto.value) countOfMatch++ }
        return Rank.getRank(countOfMatch, (bonus in winningLotto.value))
    }

    fun checkRankMany(
        manyLotto: List<Lotto>,
        winningLotto: Lotto,
        bonus: Int,
    ): Map<Rank, Int> {
        val rankMap = mutableMapOf<Rank, Int>()
        for (rank in Rank.entries) rankMap.putIfAbsent(rank, 0)
        for (lotto in manyLotto) {
            val rank = checkRank(lotto, winningLotto, bonus)
            rankMap[rank] = rankMap.getOrDefault(rank, 0) + 1
        }
        return rankMap.toMap()
    }

    companion object {
        fun getRate(rankMap: Map<Rank, Int>): String {
            var total = 0
            for (rank in rankMap.keys) {
                total += rank.winningMoney * rankMap[rank]!!
            }

            val sum = rankMap.values.sum() * 1000
            if (sum == 0) return "0.0"
            return String.format("%.2f", (total.toDouble() / sum.toDouble()))
        }
    }
}
