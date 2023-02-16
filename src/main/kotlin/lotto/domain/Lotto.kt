package lotto.domain

import lotto.constant.LOTTO_SIZE

class Lotto(lottoNumbers: List<LottoNumber>) {
    val lottoNumbers: List<LottoNumber> = lottoNumbers.sortedBy { it.value }

    init {
        validateLottoSize()
        validateDuplicateLottoNumber()
    }

    private fun validateLottoSize() {
        require(lottoNumbers.size == LOTTO_SIZE) { LOTTO_SIZE_ERROR }
    }

    private fun validateDuplicateLottoNumber() {
        require(lottoNumbers.distinctBy { it.value }.size == LOTTO_SIZE) { DUPLICATE_LOTTO_NUMBER_ERROR }
    }

    override fun toString(): String = lottoNumbers.map { lottoNumber -> lottoNumber.value }.toString()

    companion object {
        private const val LOTTO_SIZE_ERROR = "로또 숫자의 개수가 올바르지 않습니다."
        private const val DUPLICATE_LOTTO_NUMBER_ERROR = "로또 숫자는 중복되면 안됩니다."
    }
}
