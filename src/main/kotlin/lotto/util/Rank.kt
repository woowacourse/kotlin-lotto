package lotto.util

enum class Rank(val index: Int, val price: Int) {
    FIRST(0, 2_000_000_000),
    SECOND(1, 30_000_000),
    THIRD(2, 1_500_000),
    FOURTH(3, 50_000),
    FIFTH(4, 5_000),
    NONE(-1, 0),
}
