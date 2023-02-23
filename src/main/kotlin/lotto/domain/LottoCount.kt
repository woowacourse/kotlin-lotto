package lotto.domain

class LottoCount private constructor(val manualCount: Int, val autoCount: Int) {

    init {
        validateManualCount()
        validateAutoCount()
    }

    private fun validateManualCount() {
        require(manualCount >= MINIMUM_LOTTO_COUNT) { MANUAL_LOTTO_NEGATIVE_ERROR }
    }

    private fun validateAutoCount() {
        require(autoCount >= MINIMUM_LOTTO_COUNT) { MANUAL_LOTTO_COUNT_ERROR }
    }

    companion object {
        private const val MINIMUM_LOTTO_COUNT = 0
        private const val MANUAL_LOTTO_NEGATIVE_ERROR = "수동 개수는 음수일 수 없습니다."
        private const val MANUAL_LOTTO_COUNT_ERROR = "수동 개수는 구매 가능 개수를 초과할 수 없습니다."

        fun from(maxCount: Int, manualCount: Int): LottoCount {
            return LottoCount(manualCount, maxCount - manualCount)
        }
    }
}
