package domain

data class ComparingResult(
    val matchedCount: Int,
    val isBonusMatched: Boolean
) {
    init {
        require(matchedCount in MATCHED_RANGE) {
            "[Error] 잘못된 값 : $matchedCount matchedCount는 $MATCHED_COUNT_MINIMUM 부터 $MATCHED_COUNT_MAXIMUM 까지 가능합니다."
        }
        require(!(matchedCount == 6 && isBonusMatched)) { "[Error] 당첨번호 6개가 같다면 보너스 번호가 일치할 수 없습니다." }
    }

    companion object {
        private const val MATCHED_COUNT_MINIMUM = 0
        private const val MATCHED_COUNT_MAXIMUM = 6
        private val MATCHED_RANGE = MATCHED_COUNT_MINIMUM..MATCHED_COUNT_MAXIMUM
    }
}
