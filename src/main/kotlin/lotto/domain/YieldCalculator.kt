package lotto.domain

import kotlin.math.floor
import lotto.constants.Rank

object YieldCalculator {
    fun calculateYield(lottoCount: Int, ranks: List<Rank>): Double {
        val yield = calculatePrize(ranks).toDouble() / (lottoCount * 1000)
        return floor(yield * 100.0) / 100.0
    }

    private fun calculatePrize(ranks: List<Rank>): Int {
        var prize = 0
        ranks.forEach { rank -> prize += rank.winningMoney }
        return prize
    }
}
