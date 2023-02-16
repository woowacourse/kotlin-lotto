enum class LottoResult(val prizeMoney: Int, val matchCount: Int) {
    MISS(0, 0),
    FIFTH(5_000, 3),
    FORTH(50_000, 4),
    THIRD(1_500_000, 5),
    SECOND(30_000_000, 5),
    FIRST(2_000_000_000, 6)
}
