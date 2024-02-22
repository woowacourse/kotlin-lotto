package model

enum class Rank(val countOfMatch: Int, val winningMoney: Int, private val isBonusMatched: Boolean = false) {
    MISS(0, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000, true),
    FIRST(6, 2_000_000_000),
    ;

    fun getMessage(): String = "${countOfMatch}개 일치${if (isBonusMatched) ", 보너스 볼 일치" else ""}(${winningMoney}원)"

    companion object {
        fun valueOf(
            countOfMatch: Int,
            matchBonus: Boolean,
        ): Rank {
            return when {
                countOfMatch == SECOND.countOfMatch && matchBonus -> SECOND
                else -> entries.find { countOfMatch == it.countOfMatch } ?: MISS
            }
        }
    }
}
