package lotto.model

fun interface NumbersGenerator {
    fun generate(): List<Int>
}

class RandomNumbersGenerator(private val size: Int = LOTTO_SIZE) : NumbersGenerator {
    override fun generate(): List<Int> = LOTTO_NUM_RANGE.shuffled().take(size).sorted()

    companion object {
        private const val MIN_LOTTO_NUMBER = 1
        private const val MAX_LOTTO_NUMBER = 45
        private val LOTTO_NUM_RANGE = MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER
        private const val LOTTO_SIZE = 6
    }
}
