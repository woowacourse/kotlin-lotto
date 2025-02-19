package lotto.model

class Lotto(val numbers: List<Int>) {
    init {
        require(numbers.distinct().size == numbers.size) { "로또 번호가 중복될 수 없습니다." }
        require(numbers.size == LOTTO_NUMBERS_SIZE) { "발급하는 로또 번호는 6개여야 합니다." }
        numbers.forEach { number ->
            require(number in MINIMUM_LOTTO_RANGE..MAXIMUM_LOTTO_RANGE) { "로또 번호는 1에서 45 범위 내에서 있어야 합니다." }
        }
    }

    companion object {
        private const val LOTTO_NUMBERS_SIZE: Int = 6
        private const val MAXIMUM_LOTTO_RANGE: Int = 45
        private const val MINIMUM_LOTTO_RANGE: Int = 1
    }
}
