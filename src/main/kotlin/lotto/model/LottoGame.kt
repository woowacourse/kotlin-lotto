package lotto.model

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

private fun checkSecond(rank: LottoPrize, matchBonus: Boolean) = rank == LottoPrize.THIRD && matchBonus
