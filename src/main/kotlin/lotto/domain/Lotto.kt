package lotto.domain

import lotto.constant.LOTTO_SIZE

class Lotto(val lottoNumbers: Set<LottoNumber>) {

    init {
        validateLottoSize()
    }

    private fun validateLottoSize() {
        require(lottoNumbers.size == LOTTO_SIZE) { LOTTO_SIZE_ERROR }
    }

    companion object {
        private const val LOTTO_SIZE_ERROR = "로또 숫자의 개수가 올바르지 않거나 중복이 있습니다."
    }
}
