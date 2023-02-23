package lotto.domain

class Lotto(val lottoNumbers: Set<LottoNumber>) {

    init {
        validateLottoSize()
    }

    private fun validateLottoSize() {
        require(lottoNumbers.size == LOTTO_SIZE) { LOTTO_SIZE_ERROR }
    }

    fun countMatchedMainLottoNumber(winningLotto: WinningLotto): Int =
        lottoNumbers.count { lottoNumber -> lottoNumber.value in winningLotto.mainLottoNumbers.lottoNumbers.map { it.value } }

    fun checkMatchedBonusLottoNumber(winningLotto: WinningLotto): Boolean =
        winningLotto.bonusLottoNumber.value in lottoNumbers.map { lottoNumber -> lottoNumber.value }

    companion object {
        const val LOTTO_SIZE = 6

        private const val LOTTO_SIZE_ERROR = "로또 숫자의 개수가 올바르지 않습니다."
    }
}
