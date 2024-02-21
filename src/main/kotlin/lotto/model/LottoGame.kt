package lotto.model

import lotto.util.Constant



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
    return prize / payCount / Constant.LOTTO_PRICE
}

private fun checkSecond(rank: LottoPrize, matchBonus: Boolean) = rank == LottoPrize.THIRD && matchBonus