package domain

import kotlin.math.round

class TotalPrize(val amount: Long) {

    fun calculateEarningRate(spendPayment: Payment): Double {
        return round(amount / spendPayment.amount.toDouble() * DECIMAL_PLACES_TO_DISPLAY) / DECIMAL_PLACES_TO_DISPLAY
    }

    companion object {
        private const val DECIMAL_PLACES_TO_DISPLAY = 100
    }
}
