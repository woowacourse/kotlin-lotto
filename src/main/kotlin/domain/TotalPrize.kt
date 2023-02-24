package domain

import kotlin.math.round

class TotalPrize(val amount: Long) {

    fun getEarningRate(spendPayment: Payment): Double {
        return round(amount / spendPayment.amount.toDouble() * 100) / 100
    }
}
