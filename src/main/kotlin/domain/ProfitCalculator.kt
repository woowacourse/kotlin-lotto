package domain

import domain.model.LottoResult
import domain.model.PurchaseMoney
import kotlin.math.floor

class ProfitCalculator {
    fun getProfit(purchaseMoney: PurchaseMoney, lottoResults: List<LottoResult>) =
        lottoResults.sumOf { lottoResult ->
            lottoResult.prizeMoney
        }.formatProfitRate(purchaseMoney)

    private fun Int.formatProfitRate(purchaseMoney: PurchaseMoney) =
        floor((this.toDouble() / purchaseMoney.money) * DECIMAL_FORMAT) / DECIMAL_FORMAT.toDouble()

    companion object{
        private const val DECIMAL_FORMAT = 100
    }
}
