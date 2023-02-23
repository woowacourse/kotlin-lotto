package lotto.domain

object YieldRateCalculator {

    fun getYieldRate(purchaseMoney: PurchaseMoney, totalPrizeMoney: Int): Double =
        totalPrizeMoney / purchaseMoney.value.toDouble()
}
