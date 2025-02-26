package lotto.model

class WinningLotto(
    val numbers: LottoNumbers,
    val bonusNumber: LottoNumber,
) {
    fun findRank(other: Lotto): Rank = Rank.valueOf(countMatchedNumber(other), findBonusNumber(other))

    private fun countMatchedNumber(other: Lotto): Int = numbers.numberList.count { other.findNumber(it) }

    private fun findBonusNumber(other: Lotto): Boolean = other.findNumber(bonusNumber)

    companion object {
        fun validation(
            numbers: LottoNumbers,
            bonusNumber: LottoNumber,
        ): ValidationResult {
            if (numbers.include(bonusNumber)) return ValidationResult.Error.DuplicateError
            return ValidationResult.Success
        }
    }
}
