package lottogame.model.generator

fun interface NumbersGenerator {
    fun generate(
        size: Int,
        range: IntRange,
    ): List<Int>
}

class RandomNumberGenerator : NumbersGenerator {
    override fun generate(
        size: Int,
        range: IntRange,
    ): List<Int> =
        range
            .shuffled()
            .take(size)
            .sorted()
}
