package lotto

class RandomGenerator {
    fun getRandomNumberList(): List<Int> {
        return (MIN_BOUND..MAX_BOUND).shuffled().take(NUMBER_OF_LOTTO_NUMBER)
    }

    companion object {
        const val MAX_BOUND: Int = 45
        const val MIN_BOUND: Int = 1
        const val NUMBER_OF_LOTTO_NUMBER: Int = 6
    }
}
