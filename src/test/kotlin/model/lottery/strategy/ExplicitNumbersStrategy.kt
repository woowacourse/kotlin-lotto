package model.lottery.strategy

class ExplicitNumbersStrategy(private val list: List<Int>) : NumbersStrategy {
    override fun generate(): List<Int> = list.sorted()
}
