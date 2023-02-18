package domain.lotto

import domain.lotto.number.LottoNumber
import util.common.constant.ERROR_PREFIX

open class Lotto(numbers: List<LottoNumber>) : List<LottoNumber> by numbers {
    init {
        validateLottoNumbers()
    }

    private fun validateLottoNumbers() {
        validateLottoNumbersSize()
        validateLottoNumbersDuplication()
    }

    private fun validateLottoNumbersSize() {
        require(size == LOTTO_SIZE) { ERROR_MESSAGE_INVALID_LOTTO_SIZE }
    }

    private fun validateLottoNumbersDuplication() {
        check(distinctBy { it.value }.size == LOTTO_SIZE) { ERROR_MESSAGE_DUPLICATED_LOTTO_NUMBER }
    }

    companion object {
        const val LOTTO_SIZE = 6
        private const val ERROR_MESSAGE_INVALID_LOTTO_SIZE = "$ERROR_PREFIX 로또 번호는 ${LOTTO_SIZE}개이어야 합니다."
        private const val ERROR_MESSAGE_DUPLICATED_LOTTO_NUMBER = "$ERROR_PREFIX 로또 번호는 중복되지 않아야 합니다."
    }
}
