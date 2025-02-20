package lotto.model

enum class Rank(
    val matchCount: Int,
    private val isMatchedBonus: Boolean,
    val prizeMoney: Int,
) {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    MISS(0, false, 0),

    ;

    companion object {
        private const val MATCH_COUNT_OF_SECOND_OR_THIRD = 5

        fun valueOf(
            matchCount: Int,
            isMatchedBonus: Boolean,
        ): Rank =
            when (matchCount) {
                MATCH_COUNT_OF_SECOND_OR_THIRD -> findSecondOrThird(isMatchedBonus)
                else -> entries.firstOrNull { it.matchCount == matchCount } ?: MISS
            }

        private fun findSecondOrThird(isMatchedBonus: Boolean): Rank =
            when (isMatchedBonus) {
                SECOND.isMatchedBonus -> SECOND
                else -> THIRD
            }
    }
}
