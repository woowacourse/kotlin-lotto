package lotto.model

class LottoCount(private val purchasableLottoCount: Int, inputManualLottoCount: String) {
    val manualLottoCount = inputManualLottoCount.toIntOrNull() ?: throw IllegalArgumentException(ERROR_INPUT_TYPE_MESSAGE)
    val autoLottoCount = purchasableLottoCount - manualLottoCount

    init {
        validateManualLottoCount()
    }

    private fun validateManualLottoCount() {
        require(manualLottoCount in MIN_MANUAL_LOTTO_COUNT..purchasableLottoCount) {
            ERROR_MANUAL_LOTTO_COUNT
        }
    }

    companion object {
        private const val MIN_MANUAL_LOTTO_COUNT = 0
        private const val ERROR_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수는 구매 가능한 로또 수보다 작거나 같아야 합니다."
        private const val ERROR_INPUT_TYPE_MESSAGE = "수동으로 구매할 로또 수는 숫자만 입력 가능합니다."
    }
}
