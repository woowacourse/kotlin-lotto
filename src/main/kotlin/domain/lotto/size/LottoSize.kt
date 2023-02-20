package domain.lotto.size

import util.common.constant.ERROR_PREFIX

@JvmInline
value class LottoSize private constructor(val value: Int) {
    init {
        require(value > MIN_LOTTO_SIZE) { ERROR_MESSAGE_NOT_POSITIVE_LOTTO_SIZE }
    }

    companion object {
        private const val MIN_LOTTO_SIZE = 0
        private const val ERROR_MESSAGE_NOT_POSITIVE_LOTTO_SIZE = "$ERROR_PREFIX 로또 개수는 0보다 큰 수여야 합니다."
        private const val ERROR_MESSAGE_NOT_NUMERIC_LOTTO_SIZE = "$ERROR_PREFIX 로또 개수는 숫자여야 합니다."

        fun from(value: String): LottoSize {
            val lottoSize = requireNotNull(value.trim().toIntOrNull()) { ERROR_MESSAGE_NOT_NUMERIC_LOTTO_SIZE }
            return LottoSize(lottoSize)
        }
    }
}
