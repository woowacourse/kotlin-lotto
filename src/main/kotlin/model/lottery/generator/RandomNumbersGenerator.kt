package model.lottery.generator

class RandomNumbersGenerator(private val size: Int, private val range: IntRange) : NumbersGenerator {
    override fun generate(): List<Int> = range.shuffled().take(size).sorted()
}
