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
        var countOfMatch = 0
        val notHitNums = mutableListOf<Int>()
        winningLotto.value.forEach { num ->
            if (num in lotto.value) {
                countOfMatch++
            } else {
                notHitNums.add(num)
            }
        }
        return Rank.getRank(countOfMatch, (bonus in notHitNums))
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

            val sum = rankMap.values.sum() * LOTTO_PRICE
            if (sum == 0) return "0.0"
            return String.format("%.2f", (total.toDouble() / sum.toDouble()))
        }
    }
}
