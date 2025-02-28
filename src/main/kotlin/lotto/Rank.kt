package lotto

enum class Rank(
    val countOfMatch: Int,
    val winningMoney: Int,
    val matchBonus: Boolean,
) {
    FIRST(6, 2_000_000_000, false),
    SECOND(5, 30_000_000, true),
    THIRD(5, 1_500_000, false),
    FOURTH(4, 50_000, false),
    FIFTH(3, 5_000, false),
    MISS(0, 0, false),
    ;

    companion object {
        fun valueOf(
            countOfMatch: Int,
            matchBonus: Boolean,
        ): Rank =
            entries.firstOrNull {
                (it.countOfMatch == countOfMatch) && ((it.matchBonus && matchBonus) == it.matchBonus)
            } ?: throw IllegalArgumentException(RANK_FIND_ERROR)

        const val RANK_FIND_ERROR = "[ERROR] 순위를 찾을 수 없습니다"
    }
}
