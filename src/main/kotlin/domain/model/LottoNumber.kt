package domain.model

import domain.model.Lotto.Companion.ERROR
import domain.service.AutoLottoGenerator.Companion.LOTTO_MAX
import domain.service.AutoLottoGenerator.Companion.LOTTO_MIN

@JvmInline
value class LottoNumber(
    val value: Int,
) {
    init {
        require(value in LOTTO_MIN..LOTTO_MAX) { INVALID_BONUS_NUMBER }
    }

    companion object {
        const val INVALID_BONUS_NUMBER = "$ERROR 로또 번호는 1부터 45 사이입니다."
    }
}
