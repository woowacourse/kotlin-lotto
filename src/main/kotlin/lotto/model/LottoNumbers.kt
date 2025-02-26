package lotto.model

import kotlin.collections.distinct

class LottoNumbers(
    val numberList: List<LottoNumber>,
) {
    init {
        require(numberList.size == LOTTO_NUMBER_SIZE) { NUMBER_SIZE_ERROR }
        require(numberList.distinct().size == numberList.size) { NUMBER_DUPLICATE_ERROR }
    }

    fun include(number: LottoNumber): Boolean = numberList.contains(number)

    companion object {
        const val LOTTO_NUMBER_SIZE = 6
        const val NUMBER_SIZE_ERROR = "[ERROR] 로또 번호의 갯수가 ${LOTTO_NUMBER_SIZE}개가 아닙니다"
        const val NUMBER_DUPLICATE_ERROR = "[ERROR] 로또 번호가 중복되었습니다."

        fun validation(numberList: List<Int>): ValidationResult {
            when {
                numberList.size != LOTTO_NUMBER_SIZE -> return ValidationResult.Error.NumberSizeError
                numberList.distinct().size != numberList.size -> return ValidationResult.Error.DuplicateError
            }
            numberList.forEach {
                if (LottoNumber.validation(it) == ValidationResult.Error.NumberRangeError) return ValidationResult.Error.NumberRangeError
            }
            return ValidationResult.Success
        }
    }
}
