package lotto.model

enum class Rank(
    val matchCount: Int,
    val prizeMoney: Int,
) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0),

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
                true -> SECOND
                false -> THIRD
            }
    }
}
