package lotto

class PrizeAmountManager {
    fun calculateAllAmount(rankList: List<Rank>): Int {
        val amount = 0
        rankList.forEach {
            amount + it.winningMoney
        }
        return amount
    }

    fun calculateRate(
        inputMoney: Int,
        allAmount: Int,
    ): Double {
        return allAmount.toDouble() / inputMoney.toDouble()
    }
}
