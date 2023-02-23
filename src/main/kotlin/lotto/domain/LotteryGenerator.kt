package lotto.domain

fun interface LotteryGenerator {
    fun generate(): Lottery
}
