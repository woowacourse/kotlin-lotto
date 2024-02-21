package model

enum class Rank(val countOfMatch: Int, val winningMoney: Int) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0),
    ;

    companion object {
        private const val MIN_COUNT = 0
        private const val MISS_MAX_THRESHOLD = 2
        private const val MAX_COUNT = 6
        private val MISS_RANGE = MIN_COUNT..MISS_MAX_THRESHOLD
        private val NON_BONUS_COUNTS = listOf(3, 4, 6)

        fun valueOf(
            countOfMatch: Int,
            matchBonus: Boolean,
        ): Rank {
            return when {
                countOfMatch in MISS_RANGE -> MISS
                countOfMatch == SECOND.countOfMatch && matchBonus -> SECOND
                countOfMatch == THIRD.countOfMatch -> THIRD
                countOfMatch in NON_BONUS_COUNTS -> entries.first { it.countOfMatch == countOfMatch }
                else -> throw IllegalArgumentException("$countOfMatch - countOfMatch 는 $MIN_COUNT ~ $MAX_COUNT 사이 값이어야합니다.")
            }
        }
    }
}
