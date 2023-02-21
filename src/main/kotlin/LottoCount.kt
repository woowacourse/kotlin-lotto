class LottoCount(val manualCount: Count, val totalCount: Count) {

    val automaticCount: Count = runCatching { Count(totalCount.value - manualCount.value) }.getOrElse {
        throw IllegalArgumentException(MANUAL_LOTTO_COUNT_ERROR_MESSAGE)
    }

    init {
        require(totalCount.value != ZERO) { TOTAL_LOTTO_COUNT_ERROR_MESSAGE }
    }

    companion object {
        const val MANUAL_LOTTO_COUNT_ERROR_MESSAGE = "[ERROR] 수동 로또 개수가 총 로또 개수보다 많을 수 없습니다."
        const val ZERO = 0
        const val TOTAL_LOTTO_COUNT_ERROR_MESSAGE = "[ERROR] 총 로또 개수가 0이 될순 없습니다"
    }
}
