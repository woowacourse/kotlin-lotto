package lotto.domain

class TotalPurchasableLottoCount(
    val manualLottoCount: LottoCount,
    purchasableCount: LottoCount,
) {
    val autoLottoCount: LottoCount

    init {
        require(manualLottoCount.count >= MIN_BOUND) { ERROR_MANUAL_LOTTO_COUNT_MUST_BE_NON_NEGATIVE }
        require(manualLottoCount.count <= purchasableCount.count) { ERROR_MANUAL_LOTTO_COUNT_EXCEEDS_PURCHASABLE }

        autoLottoCount = LottoCount(purchasableCount.count - manualLottoCount.count)
    }

    companion object {
        private const val MIN_BOUND = 0

        private const val ERROR_MANUAL_LOTTO_COUNT_MUST_BE_NON_NEGATIVE = "[ERROR] 수동 로또 개수는 0이상 이어야 합니다."
        private const val ERROR_MANUAL_LOTTO_COUNT_EXCEEDS_PURCHASABLE = "[ERROR] 구입 가능한 로또의 개수보다 많을 수 없습니다."
    }
}
