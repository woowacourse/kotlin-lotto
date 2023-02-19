package domain

import java.lang.IllegalArgumentException

enum class Rank(val countOfMatch: Int, val winningMoney: Int) {
    FIRST(6, 2_000_000_000) {
        override fun match(countOfMatch: Int, matchBonus: Boolean): Boolean =
            this.countOfMatch == countOfMatch && matchBonus.not()
    },
    SECOND(5, 30_000_000) {
        override fun match(countOfMatch: Int, matchBonus: Boolean): Boolean =
            this.countOfMatch == countOfMatch && matchBonus
    },
    THIRD(5, 1_500_000) {
        override fun match(countOfMatch: Int, matchBonus: Boolean): Boolean =
            this.countOfMatch == countOfMatch && matchBonus.not()
    },
    FOURTH(4, 50_000) {
        override fun match(countOfMatch: Int, matchBonus: Boolean): Boolean =
            this.countOfMatch == countOfMatch
    },
    FIFTH(3, 5_000) {
        override fun match(countOfMatch: Int, matchBonus: Boolean): Boolean =
            this.countOfMatch == countOfMatch
    },
    MISS(2, 0) {
        override fun match(countOfMatch: Int, matchBonus: Boolean): Boolean =
            countOfMatch in 0..this.countOfMatch
    },
    ;

    abstract fun match(countOfMatch: Int, matchBonus: Boolean): Boolean

    companion object {
        fun valueOf(countOfMatch: Int, matchBonus: Boolean): Rank {
            return values().find { it.match(countOfMatch, matchBonus) }
                ?: throw IllegalArgumentException(ERROR_IMPOSSIBLE_CASE.format(countOfMatch, matchBonus))
        }

        private const val ERROR_IMPOSSIBLE_CASE = "등수가 나올 수 없는 경우입니다.\n잘못된 값: {매치 카운트=%d, 매치 보너스=%s}"
    }
}
