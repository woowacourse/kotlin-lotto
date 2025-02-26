package lotto.model

class WinningLotto(
    val numbers: LottoNumbers,
    val bonusNumber: LottoNumber,
) {
    init {
        require(!numbers.include(bonusNumber)) { NUMBER_DUPLICATED_ERROR }
    }

    fun findRank(other: Lotto): Rank = Rank.valueOf(countMatchedNumber(other), findBonusNumber(other))

    private fun countMatchedNumber(other: Lotto): Int = numbers.numberList.count { other.findNumber(it) }

    private fun findBonusNumber(other: Lotto): Boolean = other.findNumber(bonusNumber)

    companion object {
        const val NUMBER_DUPLICATED_ERROR = "[ERROR] 당첨 번호와 보너스 볼은 중복될 수 없습니다."

        fun validation(
            numbers: LottoNumbers,
            bonusNumber: LottoNumber,
        ): ValidationResult {
            if (numbers.include(bonusNumber)) return ValidationResult.Error.DuplicateError
            return ValidationResult.Success
        }
    }
}
