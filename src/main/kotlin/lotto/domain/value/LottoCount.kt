package lotto.domain.value

@JvmInline
value class LottoCount(
    val count: Int,
) {
    init {
        require(count >= MIN_LOTTO_COUNT) { ERROR_LOTTO_COUNT_RANGE }
    }

    fun subtract(other: LottoCount): LottoCount = LottoCount(count - other.count)

    companion object {
        private const val MIN_LOTTO_COUNT = 0
        private const val ERROR_LOTTO_COUNT_RANGE = "[ERROR] 로또 수는 0 이상이고 구매한 수량보다 작아야 합니다."
    }
}
