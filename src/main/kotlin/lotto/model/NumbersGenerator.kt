package lotto.model

fun interface NumbersGenerator {
    fun generate(): List<Int>
}

class RandomNumbersGenerator(private val size: Int = LOTTO_SIZE) : NumbersGenerator {
    override fun generate(): List<Int> =
        (1..45)
            .shuffled()
            .take(size)
            .sorted()

    companion object {
        private const val LOTTO_SIZE = 6
    }
}
