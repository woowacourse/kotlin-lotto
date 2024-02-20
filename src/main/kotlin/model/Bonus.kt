package model

class Bonus private constructor(val number: Int, private val lottoNumbers: List<Int>) {
    constructor(input: String, lottoNumbers: List<Int>) :
        this(input.toIntOrNull() ?: throw IllegalArgumentException(EXCEPTION_IS_NOT_NUMBER), lottoNumbers)

    init {
        require(validateRange(number)) { EXCEPTION_INVALID_RANGE }
        require(validateUnique(number, lottoNumbers)) { EXCEPTION_DUPLICATED_NUMBER }
    }

    private fun validateRange(number: Int) = (MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER).contains(number)

    private fun validateUnique(
        number: Int,
        lottoNumbers: List<Int>,
    ) = !lottoNumbers.contains(number)

    companion object {
        private const val MIN_LOTTO_NUMBER = 1
        private const val MAX_LOTTO_NUMBER = 45
        const val EXCEPTION_IS_NOT_NUMBER = "숫자만 입력하셔야 합니다"
        const val EXCEPTION_INVALID_RANGE = "보너스 번호는 1 ~ 45 사이 숫자여야 합니다"
        const val EXCEPTION_DUPLICATED_NUMBER = "보너스 번호는 로또 번호에 포함될 수 없습니다"
    }
}
