package domain

object Bank {

    fun getEarningRate(totalPrize: Long, spendMoney: Money): Double {
        return totalPrize / spendMoney.amount.toDouble()
    }
}
