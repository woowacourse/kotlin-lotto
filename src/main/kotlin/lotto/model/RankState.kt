package lotto.model

enum class RankState {
    WINNING,
    BONUS_WINNING,
    DOES_NOT_WINNING, ;

    companion object {
        private const val CORRECT_ZERO = 0
        private const val CORRECT_TWO = 2
        private const val CORRECT_THREE = 3
        private const val CORRECT_FOUR = 4
        private const val CORRECT_FIVE = 5
        private const val CORRECT_SIX = 6

        fun valueOf(
            countOfMatch: Int,
            matchBonus: Boolean,
        ): RankState {
            return when (countOfMatch) {
                in CORRECT_ZERO..CORRECT_TWO -> DOES_NOT_WINNING
                in CORRECT_THREE..CORRECT_FOUR -> WINNING
                CORRECT_FIVE -> if (matchBonus) BONUS_WINNING else WINNING
                CORRECT_SIX -> WINNING
                else -> DOES_NOT_WINNING
            }
        }
    }
}
