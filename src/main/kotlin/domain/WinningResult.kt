package domain

class WinningResult(val winningRankResult: Map<Rank, Int>) {

    fun getWinningMoney(): Int {
        return winningRankResult.map { it.key.winningMoney * it.value }.sum()
    }

    fun calculateYield(money: Money): Double {
        return getWinningMoney() / money.price.toDouble()
    }
}
