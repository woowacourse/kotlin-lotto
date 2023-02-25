package lotto.domain

class Lotteries(
    val lotteries: List<Lottery>
) {
    val size: Int
        get() = lotteries.size

    fun get(index: Int): Lottery = lotteries[index]
}
