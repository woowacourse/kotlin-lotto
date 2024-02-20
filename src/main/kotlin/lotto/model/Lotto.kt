package lotto.model

class Lotto(val numbers: List<String>) {
    init {
        require(numbers.distinct().size == LOTTO_SIZE) { LOTTO_SIZE_ERROR_MESSAGE }
        require(numbers.all { it.toIntOrNull() in LOTTO_NUMBER_RANGE }) { LOTTO_RANGE_ERROR_MESSAGE }
    }

    companion object {
        const val LOTTO_SIZE = 6
        val LOTTO_NUMBER_RANGE: IntRange = 1..45

        const val LOTTO_SIZE_ERROR_MESSAGE = "로또의 숫자들은 중복되지 않는 6개입니다."
        const val LOTTO_RANGE_ERROR_MESSAGE = "로또의 숫자들은 1부터 45까지의 숫자로 구성되어야 합니다."
    }
}
