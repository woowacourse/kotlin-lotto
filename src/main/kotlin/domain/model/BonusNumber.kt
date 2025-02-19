package domain.model

import domain.generator.LottoGenerator.Companion.LOTTO_MAX
import domain.generator.LottoGenerator.Companion.LOTTO_MIN
import domain.model.Lotto.Companion.ERROR

@JvmInline
value class BonusNumber(val value: Int) {
    init {
        require(value in LOTTO_MIN..LOTTO_MAX) { INVALID_BONUS_NUMBER }
    }

    companion object {
        const val INVALID_BONUS_NUMBER = "$ERROR 보너스 볼 번호는 1부터 45 사이입니다."
    }
}
