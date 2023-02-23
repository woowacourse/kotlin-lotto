package domain

class LottoPurchaseInfo(
    val purchaseLottoMoney: PurchaseLottoMoney,
    val manualCount: Int
) {
    val totalCount: Int
        get() = purchaseLottoMoney.purchaseCount

    val autoCount: Int
        get() = totalCount - manualCount

    val money: Int
        get() = purchaseLottoMoney.money

    init {
        require(purchaseLottoMoney.purchaseCount >= manualCount) {
            ERROR_MANUAL_LOTTO_COUNT_OVER_TOTAL_COUNT.format(manualCount, totalCount)
        }
        require(manualCount >= 0) {
            ERROR_MANUAL_COUNT_UNDER_ZERO.format(manualCount)
        }
    }

    companion object {
        private const val ERROR_MANUAL_LOTTO_COUNT_OVER_TOTAL_COUNT =
            "[ERROR] 현재 수동 발급 요청 개수는 %d개 가능한 총개수는 %d개, 수동 발급 개수는 발급 가능한 총 개수를 넘을 수 없습니다."
        private const val ERROR_MANUAL_COUNT_UNDER_ZERO =
            "[ERROR] 현재 수동 발급 요청 개수는 %d, 수동 발급 개수는 0이상의 수여야 합니다."
    }
}
