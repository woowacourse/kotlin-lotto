package lotto.model

class LottoCalculator {
    fun getRateOfReturn(
        totalPrizeMoney: Long,
        purchaseMoney: Int,
    ): Double {
        val rateOfReturn: Double = totalPrizeMoney / purchaseMoney.toDouble()
        return rateOfReturn
    }

    fun getIsLossMoney(rateOfReturn: Double): Boolean = rateOfReturn < 1
}
