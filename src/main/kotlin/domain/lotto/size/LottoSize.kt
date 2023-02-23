package domain.lotto.size

import util.common.constant.ERROR_PREFIX

data class LottoSize(val value: Int) {
    init {
        require(value > MIN_LOTTO_SIZE) { ERROR_MESSAGE_NOT_POSITIVE_LOTTO_SIZE }
    }

    companion object {
        private const val MIN_LOTTO_SIZE = 0
        private const val ERROR_MESSAGE_NOT_POSITIVE_LOTTO_SIZE = "$ERROR_PREFIX 로또 개수는 0보다 큰 수여야 합니다."
    }
}
