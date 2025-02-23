package lotto.model

class Lotto(
    val numbers: List<Int>,
) {
    init {
        require(numbers.distinct().size == numbers.size) { LOTTO_DISTINCT_MESSAGE }
        require(numbers.size == LOTTO_NUMBERS_SIZE) { LOTTO_COUNT_MESSAGE }
        numbers.forEach { number ->
            require(number in MINIMUM_LOTTO_RANGE..MAXIMUM_LOTTO_RANGE) { LOTTO_BOUND_MESSAGE }
        }
    }

    companion object {
        const val LOTTO_NUMBERS_SIZE: Int = 6
        const val MAXIMUM_LOTTO_RANGE: Int = 45
        const val MINIMUM_LOTTO_RANGE: Int = 1
        const val LOTTO_COUNT_MESSAGE = "로또 번호는 6개여야 합니다."
        const val LOTTO_BOUND_MESSAGE = "로또 번호는 1에서 45 범위 내에서 있어야 합니다."
        const val LOTTO_DISTINCT_MESSAGE = "로또 번호가 중복될 수 없습니다."
    }
}
