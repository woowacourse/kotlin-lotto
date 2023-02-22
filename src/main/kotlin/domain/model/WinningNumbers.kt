package domain.model

import domain.model.lotto.LottoNumber

class WinningNumbers(
    val catchNumbers: Set<LottoNumber>,
    val bonusNumber: LottoNumber
) {
    init {
        validateCatchNumbers()
        validateBonusNumber()
    }

    private fun validateCatchNumbers() {
        require(catchNumbers.size == CATCH_NUMBERS_COUNT) {
            CATCH_NUMBER_COUNT_ERROR
        }
    }

    private fun validateBonusNumber() {
        require(!catchNumbers.contains(bonusNumber)) {
            OVERLAPPED_ERROR
        }
    }

    fun getMatchCount(numbers: Set<LottoNumber>) =
        numbers.count { number ->
            catchNumbers.contains(number)
        }

    companion object {
        private const val CATCH_NUMBERS_COUNT = 6
        private const val CATCH_NUMBER_COUNT_ERROR = "[ERROR] 당첨 번호는 6개의 숫자로 이루어져야 합니다."
        private const val OVERLAPPED_ERROR = "[ERROR] 당첨 번호와 보너스 볼은 중복될 수 없습니다."
    }
}
