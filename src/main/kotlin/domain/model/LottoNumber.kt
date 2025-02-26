package domain.model

import ERROR
import domain.strategy.AutoLottoGenerator.Companion.LOTTO_MAX
import domain.strategy.AutoLottoGenerator.Companion.LOTTO_MIN

@JvmInline
value class LottoNumber(val number: Int) {
    init {
        require(number in LOTTO_MIN..LOTTO_MAX) { INVALID_LOTTO_NUMBERS }
    }

    companion object {
        const val INVALID_LOTTO_NUMBERS = "$ERROR 로또 번호는 ${LOTTO_MIN}부터 $LOTTO_MAX 사이입니다."
    }
}
