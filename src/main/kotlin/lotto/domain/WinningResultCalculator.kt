package lotto.domain

import java.math.BigDecimal
import java.math.RoundingMode

class WinningResultCalculator {
    fun calculateProfit(purchase: PurchaseAmount, prize: Long): Double {
        return BigDecimal(prize).divide(BigDecimal(purchase.amount), 2, RoundingMode.FLOOR).toDouble()
    }
}
