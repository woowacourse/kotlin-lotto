package lotto.model

class LottoCalculator {
    fun getRateOfReturn(
        totalPrizeMoney: Double,
        purchaseMoney: Int,
    ): Double {
        val rateOfReturn: Double = totalPrizeMoney / purchaseMoney

        return rateOfReturn
    }

    fun getIsLossMoney(rateOfReturn: Double): Boolean = rateOfReturn < 1
}
