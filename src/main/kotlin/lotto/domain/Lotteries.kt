package lotto.domain

class Lotteries(
    private val lotteries: List<Lottery>
) {
    val size: Int
        get() = lotteries.size

    fun get(index: Int): Lottery = lotteries[index]

    fun plus(newLotteries: Lotteries): Lotteries = Lotteries(lotteries + newLotteries.lotteries)
}
