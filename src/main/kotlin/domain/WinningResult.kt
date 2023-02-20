package domain

class WinningResult(val result: Map<Rank, Int>) {

    fun getWinningMoney(): Int {
        return result.map { it.key.winningMoney * it.value }.sum()
    }

    fun calculateYield(money: Money): Double {
        return getWinningMoney() / money.price.toDouble()
    }
}
