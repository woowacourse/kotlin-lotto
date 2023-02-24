package lotto.domain

class WinningLotto(val mainLottoNumbers: Lotto, val bonusLottoNumber: LottoNumber) {
    init {
        validateDuplicateLottoNumbers()
    }

    private fun validateDuplicateLottoNumbers() {
        require(bonusLottoNumber.value !in mainLottoNumbers.lottoNumbers.map { it.value }) { LOTTO_DUPLICATE_ERROR }
    }

    fun checkMatchedBonusLottoNumber(lotto: Lotto): Boolean =
        lotto.containsBonusLottoNumber(bonusLottoNumber)

    fun countMatchedMainLottoNumber(lotto: Lotto): Int =
        lotto.countContainsMainLottoNumber(mainLottoNumbers)

    companion object {
        private const val LOTTO_DUPLICATE_ERROR = "로또 메인 번호와 보너스 번호에 중복이 있습니다."
    }
}
