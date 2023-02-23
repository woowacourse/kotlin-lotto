package inputvalidation

import domain.Lotto
import domain.LottoNumber
import domain.WinningNumber

class BonusNumberValidation {

    fun checkBonusNumberValidation(input: String?, winningLotto: Lotto): Result<WinningNumber> {
        if (input == null) return Result.failure(IllegalStateException(INPUT_IS_EMPTY_ERROR_MESSAGE))
        val bonusNumber = input.toIntOrNull() ?: return Result.failure(IllegalStateException(NOT_INTEGER_ERROR))
        if (bonusNumber > LottoValidation.MAXIMUM_LOTTO_NUMBER || bonusNumber < LottoValidation.MINIMUM_LOTTO_NUMBER) return Result.failure(
            IllegalArgumentException(LottoValidation.LOTTO_NUMBER_RANGE_ERROR_MESSAGE)
        )
        if (winningLotto.contains(LottoNumber(bonusNumber))) return Result.failure(
            IllegalStateException(
                DUPLICATE_NUMBER_MESSAGE
            )
        )
        return Result.success(WinningNumber(winningLotto, LottoNumber(bonusNumber)))
    }

    companion object {
        const val NOT_INTEGER_ERROR = "정수만 입력해 주세요!!"
        const val INPUT_IS_EMPTY_ERROR_MESSAGE = "아무것도 입력하지 않았습니다."
        const val DUPLICATE_NUMBER_MESSAGE = "당첨 번호에 없는 수를 입력해 주세요"
    }
}