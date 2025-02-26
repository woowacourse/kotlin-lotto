package domain.model

import ERROR
import domain.strategy.AutoLottoGenerator.Companion.LOTTO_MAX
import domain.strategy.AutoLottoGenerator.Companion.LOTTO_MIN

@JvmInline
value class BonusNumber(val value: Int) {
    init {
        require(value in LOTTO_MIN..LOTTO_MAX) { INVALID_BONUS_NUMBER }
    }

    companion object {
        const val INVALID_BONUS_NUMBER = "$ERROR 보너스 볼 번호는 ${LOTTO_MIN}부터 $LOTTO_MAX 사이입니다."
    }
}
