package lotto.domain

class Lotto(val lottoNumbers: Set<LottoNumber>) {

    init {
        validateLottoSize()
    }

    private fun validateLottoSize() {
        require(lottoNumbers.size == LOTTO_SIZE) { LOTTO_SIZE_ERROR }
    }

    fun countContainLottoNumber(lotto: Lotto): Int {
        val lottoValue = lotto.lottoNumbers.map { it.value }
        return lottoNumbers.count { lottoNumber -> lottoNumber.value in lottoValue }
    }

    fun matchLottoNumber(bonusNumber: LottoNumber): Boolean =
        lottoNumbers.count { lottoNumber -> lottoNumber == bonusNumber } != 0

    companion object {
        const val LOTTO_SIZE = 6

        private const val LOTTO_SIZE_ERROR = "로또 숫자의 개수가 올바르지 않습니다."
    }
}
