package domain

class WinningResult {

    val result = mutableMapOf(
        Rank.FIFTH to 0,
        Rank.FOURTH to 0,
        Rank.THIRD to 0,
        Rank.SECOND to 0,
        Rank.FIRST to 0
    )

    fun setWinnigResult(rank: Rank) {
        if (!rank.equals(Rank.MISS)) result.put(rank, result.getOrDefault(rank, 0) + 1)
    }

    fun getWinningMoney(): Int {
        return result.map { it.key.winningMoney * it.value }.sum()
    }

    fun calculateYield(money: Money): Double {
        return getWinningMoney() / money.price!!.toDouble()
    }
}
