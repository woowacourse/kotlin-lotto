package model.lottery.generator

class ExplicitNumbersGenerator(private val list: List<Int>) : NumbersGenerator {
    override fun generate(): List<Int> = list.sorted()
}
