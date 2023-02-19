package lotto.domain

fun interface LotteryNumbersGenerator {
    fun generate(): List<Int>
}
