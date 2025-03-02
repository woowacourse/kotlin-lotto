package domain.model.lotto

import domain.model.lotto.Lotto.Companion.LOTTO_SIZE
import util.ErrorConstants.ERROR

sealed class LottoException(override val message: String?) : Throwable(message) {
    class InvalidLottoSize : LottoException(INVALID_LOTTO_SIZE)

    class DuplicatedLottoSize : LottoException(DUPLICATED_LOTTO_NUMBERS)

    companion object {
        private const val INVALID_LOTTO_SIZE = "$ERROR 로또 번호는 ${LOTTO_SIZE}개 입니다."
        private const val DUPLICATED_LOTTO_NUMBERS = "$ERROR 로또 번호는 중복될 수 없습니다."
    }
}
