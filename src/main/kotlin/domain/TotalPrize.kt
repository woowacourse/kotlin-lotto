package domain

class TotalPrize(val amount: Long) {
    constructor(amount: Int) : this(amount.toLong())

    fun getEarningRate(spendPayment: Payment): Double {
        return amount / spendPayment.amount.toDouble()
    }
}
