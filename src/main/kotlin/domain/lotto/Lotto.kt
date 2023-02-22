package domain.lotto

import domain.lotto.number.LottoNumber
import util.common.constant.ERROR_PREFIX

open class Lotto(numbers: Set<LottoNumber>) : Set<LottoNumber> by numbers {
    init {
        validateLottoNumbers()
    }

    private fun validateLottoNumbers() {
        require(size == LOTTO_SIZE) { ERROR_MESSAGE_INVALID_LOTTO_NUMBERS }
    }

    companion object {
        const val LOTTO_SIZE = 6
        private const val ERROR_MESSAGE_INVALID_LOTTO_NUMBERS =
            "$ERROR_PREFIX 로또 번호가 ${LOTTO_SIZE}개가 아니거나, 중복되는 번호가 있습니다."
    }
}
