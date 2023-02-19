package lotto.domain

import lotto.constant.BONUS_LOTTO_NUMBER_SIZE
import lotto.constant.LOTTO_SIZE

class WinningLotto(val mainLottoNumbers: List<LottoNumber>, val bonusLottoNumber: LottoNumber) {
    init {
        validateLottoSize()
        validateDuplicateLottoNumbers()
    }

    private fun validateLottoSize() {
        require(mainLottoNumbers.size == LOTTO_SIZE) { LOTTO_SIZE_ERROR }
    }

    private fun validateDuplicateLottoNumbers() {
        require(combineLottoNumbers().distinctBy { lottoNumber -> lottoNumber.value }.size == TOTAL_LOTTO_SIZE) { LOTTO_DUPLICATE_ERROR }
    }

    private fun combineLottoNumbers(): List<LottoNumber> = mainLottoNumbers + bonusLottoNumber

    companion object {
        private const val LOTTO_SIZE_ERROR = "로또 숫자의 개수가 올바르지 않습니다."
        private const val LOTTO_DUPLICATE_ERROR = "로또 메인 번호와 보너스 번호에 중복이 있습니다."
        private const val TOTAL_LOTTO_SIZE = LOTTO_SIZE + BONUS_LOTTO_NUMBER_SIZE
    }
}
