package lotto.domain.model

class PurchaseCount private constructor(
    val manualCount: Int,
    val autoCount: Int,
) {
    init {
        require(manualCount >= 0) { ERROR_INVALID_COUNT_RANGE }
    }

    companion object {
        private const val ERROR_INVALID_MANUAL_COUNT = "구입 금액을 넘겨서 구매할 수 없습니다."
        private const val ERROR_INVALID_COUNT_RANGE = "구매할 로또 수는 0보다 커야 합니다."

        fun from(
            totalCount: Int,
            manualCount: Int,
        ): PurchaseCount {
            require(manualCount <= totalCount) { ERROR_INVALID_MANUAL_COUNT }
            val autoCount = totalCount - manualCount
            return PurchaseCount(manualCount, autoCount)
        }
    }
}
