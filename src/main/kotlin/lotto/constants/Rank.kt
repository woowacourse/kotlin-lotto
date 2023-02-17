package lotto.constants

enum class Rank(val countOfMatch: Int, val winningMoney: Int) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0);

    companion object {
        fun valueOf(countOfMatch: Int, matchBonus: Boolean): Rank {
            if (matchBonus and (countOfMatch == 5)) {
                return SECOND
            }
            if (countOfMatch in 1..2) {
                return MISS
            }
            return try {
                values().last { rank -> rank.countOfMatch == countOfMatch }
            } catch (e: IllegalStateException) {
                println("[ERROR] " + e.message)
                MISS
            }
        }
    }
}
