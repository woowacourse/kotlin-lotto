package domain

class LottoPurchaseInfo(
    private val purchaseLottoMoney: PurchaseLottoMoney,
    val manualCount: Int
) {
    val totalCount: Int
        get() = purchaseLottoMoney.purchaseCount

    init {
        require(purchaseLottoMoney.purchaseCount >= manualCount) {
            ERROR_MANUAL_LOTTO_COUNT_OVER_TOTAL_COUNT.format(manualCount, totalCount)
        }
    }

    companion object {
        private const val ERROR_MANUAL_LOTTO_COUNT_OVER_TOTAL_COUNT =
            "[ERROR] 현재 수동 발급 요청 개수는 %d지만 총개수는 %d개, 수동 발급 개수는 발급 가능한 총 개수를 넘을 수 없습니다."
    }
}
