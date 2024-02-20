package lotto.model

class Lotto(val numbers: List<String>) {
    init {
        require(numbers.distinct().size == LOTTO_SIZE)
        require(numbers.all { it.toIntOrNull() in LOTTO_NUMBER_RANGE })
    }

    companion object {
        const val LOTTO_SIZE = 6
        val LOTTO_NUMBER_RANGE: IntRange = 1..45
    }
}
