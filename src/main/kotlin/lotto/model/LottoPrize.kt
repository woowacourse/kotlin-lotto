package lotto.model

enum class LottoPrize(
    private val matchNumbers: Int,
    private val price: Int
) {
    BOOM(0, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000);

    fun getMatchNumbers(): Int {
        return matchNumbers
    }

    fun getPrice(): Int {
        return price
    }
}
