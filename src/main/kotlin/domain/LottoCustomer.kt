package domain

import domain.model.PurchaseMoney

class LottoCustomer(
    val purchaseMoney: PurchaseMoney,
    val manualLottosCountToPurchase: Int,
) {
    init {
        require(purchaseMoney.getLottosCountToPurchase() >= manualLottosCountToPurchase) {
            MANUAL_LOTTOS_COUNT_ERROR
        }
    }

    fun getAutomaticLottosCountToPurchase(): Int = purchaseMoney.getLottosCountToPurchase() - manualLottosCountToPurchase

    companion object {
        private const val MANUAL_LOTTOS_COUNT_ERROR = "[ERROR] 구매하려는 로또의 개수보다 수동으로 발급받을 로또의 개수가 많을 수 없습니다."
    }
}
