package domain.model.number

import domain.model.number.LottoNumber.Companion.LOTTO_MAX
import domain.model.number.LottoNumber.Companion.LOTTO_MIN
import util.ErrorConstants.ERROR

sealed class LottoNumberException(override val message: String?) : Throwable(message) {
    class InvalidLottoNumberRange : LottoNumberException(INVALID_LOTTO_NUMBERS)

    companion object {
        const val INVALID_LOTTO_NUMBERS = "$ERROR 로또 번호는 ${LOTTO_MIN}부터 $LOTTO_MAX 사이입니다."
    }
}
