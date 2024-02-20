package lotto.model

import kotlin.math.round
import kotlin.time.times

fun matchCount(winningNumbers: Set<Int>, lotto: Set<Int>): Int {
    return winningNumbers.intersect(lotto).size
}

fun matchBonusNumber(winningNumbers: Set<Int>, bonusNum: Int): Boolean {
    return winningNumbers.contains(bonusNum)
}

fun findRanking(matchCount: Int, matchBonus: Boolean): LottoPrize {
    var rank = LottoPrize.entries.find {
        it.getMatchNumbers() == matchCount
    } ?: LottoPrize.BOOM
    if (checkSecond(rank, matchBonus)) rank = LottoPrize.SECOND
    return rank
}


fun prizeCalculate(matches: Map<LottoPrize, Int>): Long {
    return matches.entries.sumOf {
        (it.key.getPrice() * it.value).toLong()
    }
}

fun prizeRateCalculate(prize: Long, payCount: Int): Double {
    return prize / payCount / 1000.0
}

private fun checkSecond(rank: LottoPrize, matchBonus: Boolean) = rank == LottoPrize.THIRD && matchBonus