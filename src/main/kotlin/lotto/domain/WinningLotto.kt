package lotto.domain

import lotto.constant.BonusResult
import lotto.constant.LOTTO_SIZE
import lotto.constant.Rank

class WinningLotto(val mainLottoNumbers: Set<LottoNumber>, val bonusLottoNumber: LottoNumber) {
    init {
        validateLottoSize()
        validateDuplicateLottoNumbers()
    }

    private fun countMatchedMainLottoNumber(lotto: Lotto): Int =
        mainLottoNumbers.count { lottoNumber -> lottoNumber in lotto.lottoNumbers }

    private fun checkMatchedBonusLottoNumber(lotto: Lotto): BonusResult {
        if (bonusLottoNumber in lotto.lottoNumbers) return BonusResult.BONUS_MATCH
        return BonusResult.BONUS_MISMATCH
    }

    fun getRank(lotto: Lotto): Rank =
        Rank.convertToGrade(
            countMatchedMainLottoNumber(lotto),
            checkMatchedBonusLottoNumber(lotto),
        )

    private fun validateLottoSize() {
        require(mainLottoNumbers.size == LOTTO_SIZE) { LOTTO_SIZE_ERROR }
    }

    private fun validateDuplicateLottoNumbers() {
        require(bonusLottoNumber !in mainLottoNumbers) { LOTTO_DUPLICATE_ERROR }
    }

    companion object {
        private const val LOTTO_SIZE_ERROR = "로또 숫자의 개수가 올바르지 않습니다."
        private const val LOTTO_DUPLICATE_ERROR = "로또 메인 번호와 보너스 번호에 중복이 있습니다."
    }
}
