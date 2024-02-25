package model

fun interface NumbersGenerator {
    fun generate(size: Int): List<Int>
}

class RandomNumbersGenerator : NumbersGenerator {
    override fun generate(size: Int): List<Int> =
        (1..45)
            .shuffled()
            .take(size)
            .sorted()
}
