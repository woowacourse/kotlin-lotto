package lotto.model

enum class Rank(
    val countOfMatch: Int,
    val bonusRequired: Boolean,
    val winningMoney: Int,
    val printSequence: Int?,
) {
    FIRST(6, false, 2_000_000_000, 5),
    SECOND(5, true, 30_000_000, 4),
    THIRD(5, false, 1_500_000, 3),
    FOURTH(4, false, 50_000, 2),
    FIFTH(3, false, 5_000, 1),
    MISS(0, false, 0, null),
    ;

    companion object {
        fun valueOf(
            countOfMatch: Int,
            matchBonus: Boolean,
        ): Rank {
            entries.forEach {
                if (countOfMatch >= it.countOfMatch && (it.bonusRequired && matchBonus) == it.bonusRequired) return it
            }
            return MISS
        }
    }
}
