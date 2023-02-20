package lotto.domain

import java.math.BigDecimal
import java.math.RoundingMode

object ProfitCalculator {
    private const val NUMBER_OF_DECIMAL_PLACES = 2

    fun calculate(purchase: PurchaseAmount, prize: Long): Double {
        return BigDecimal(prize).divide(BigDecimal(purchase.amount), NUMBER_OF_DECIMAL_PLACES, RoundingMode.FLOOR).toDouble()
    }
}
