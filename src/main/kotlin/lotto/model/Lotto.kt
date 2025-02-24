package lotto.model

class Lotto(
    numbers: List<Int>,
) {
    val numbers: List<LottoNumber> = numbers.map { LottoNumber(it) }

    init {
        require(numbers.distinct().size == numbers.size) { ERROR_DUPLICATED_NUMBERS_MESSAGE }
        require(numbers.size == LOTTO_NUMBERS_SIZE) { ERROR_LOTTO_SIZE_MESSAGE }
    }

    companion object {
        const val LOTTO_NUMBERS_SIZE: Int = 6
        const val MAXIMUM_LOTTO_RANGE: Int = 45
        const val MINIMUM_LOTTO_RANGE: Int = 1
        private const val ERROR_DUPLICATED_NUMBERS_MESSAGE = "로또 번호가 중복될 수 없습니다."
        private const val ERROR_LOTTO_SIZE_MESSAGE = "로또 번호는 6개여야 합니다."
    }
}
