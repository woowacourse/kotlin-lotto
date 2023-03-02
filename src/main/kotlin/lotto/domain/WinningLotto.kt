package lotto.domain

class WinningLotto(val mainLottoNumbers: Lotto, val bonusLottoNumber: LottoNumber) {
    init {
        validateDuplicateLottoNumbers()
    }

    private fun validateDuplicateLottoNumbers() {
        require(bonusLottoNumber.value !in mainLottoNumbers.lottoNumbers.map { it.value }) { LOTTO_DUPLICATE_ERROR }
    }

    private fun checkMatchedBonusLottoNumber(lotto: Lotto): Boolean =
        lotto.matchLottoNumber(bonusLottoNumber)

    private fun countMatchedMainLottoNumber(lotto: Lotto): Int =
        lotto.countContainLottoNumber(mainLottoNumbers)

    fun calculateRank(lotto: Lotto): Rank =
        getRank(lotto)

    private fun getRank(lotto: Lotto): Rank =
        Rank.convertToRank(
            countMatchedMainLottoNumber(lotto),
            checkMatchedBonusLottoNumber(lotto),
        )

    companion object {
        private const val LOTTO_DUPLICATE_ERROR = "로또 메인 번호와 보너스 번호에 중복이 있습니다."
    }
}
