package domain

object RandomNumberGenerator {
    fun generate(range: IntRange, size: Int): List<Int> = range.shuffled().take(size).sorted()
}
