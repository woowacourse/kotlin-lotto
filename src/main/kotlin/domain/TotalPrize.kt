package domain

class TotalPrize(val amount: Long) {

    fun getEarningRate(spendPayment: Payment): Double {
        return amount / spendPayment.amount.toDouble()
    }
}
