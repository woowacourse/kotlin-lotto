package domain

import domain.model.LottoResult
import domain.model.PurchaseMoney
import kotlin.math.floor

class LottoCustomer(
    private val purchaseMoney: PurchaseMoney,
    val manualLottosCountToPurchase: Int,
) {

    init {
        require(purchaseMoney.getLottosCountToPurchase() >= manualLottosCountToPurchase) {
            MANUAL_LOTTOS_COUNT_ERROR
        }
    }

    fun getAutomaticLottosCountToPurchase(): Int =
        purchaseMoney.getLottosCountToPurchase() - manualLottosCountToPurchase

    fun getMyProfitRate(lottoResults: List<LottoResult>): Double = lottoResults.sumOf { lottoResult ->
        lottoResult.prizeMoney
    }.formatProfitRate(purchaseMoney)

    private fun Int.formatProfitRate(purchaseMoney: PurchaseMoney) =
        floor((this.toDouble() / purchaseMoney.money) * DECIMAL_FORMAT) / DECIMAL_FORMAT.toDouble()

    companion object {
        private const val DECIMAL_FORMAT = 100
        private const val MANUAL_LOTTOS_COUNT_ERROR = "[ERROR] 구매하려는 로또의 개수보다 수동으로 발급받을 로또의 개수가 많을 수 없습니다."
    }
}
