package domain.model

import util.ErrorConstants.ERROR

@JvmInline
value class LottoNumber(val value: Int) {
    init {
        require(value in (LOTTO_MIN..LOTTO_MAX)) { INVALID_LOTTO_NUMBERS }
    }

    companion object {
        const val LOTTO_MIN = 1
        const val LOTTO_MAX = 45

        const val INVALID_LOTTO_NUMBERS = "$ERROR 로또 번호는 ${LOTTO_MIN}부터 $LOTTO_MAX 사이입니다."
    }
}
