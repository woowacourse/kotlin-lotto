package lotto.domain.value

import lotto.constants.ErrorMessages

@JvmInline
value class LottoCount(
    val count: Int,
) {
    init {
        require(count >= MIN_LOTTO_COUNT) { ErrorMessages.INVALID_LOTTO_COUNT_RANGE }
    }

    fun subtract(other: LottoCount): LottoCount = LottoCount(count - other.count)

    companion object {
        private const val MIN_LOTTO_COUNT = 0
    }
}
