package model

enum class Rank1(val countOfMatch: Int, val winningMoney: Int) {
    FIRST(6, 2_000_000_000) {
        override fun isMatching(
            countOfMatch: Int,
            matchBonus: Boolean,
        ): Boolean {
            return (this.countOfMatch == countOfMatch)
        }
    },
    SECOND(5, 30_000_000) {
        override fun isMatching(
            countOfMatch: Int,
            matchBonus: Boolean,
        ): Boolean {
            return (this.countOfMatch == countOfMatch) && matchBonus
        }
    },
    THIRD(5, 1_500_000) {
        override fun isMatching(
            countOfMatch: Int,
            matchBonus: Boolean,
        ): Boolean {
            return (this.countOfMatch == countOfMatch) && matchBonus.not()
        }
    },
    FOURTH(4, 50_000) {
        override fun isMatching(
            countOfMatch: Int,
            matchBonus: Boolean,
        ): Boolean {
            return (this.countOfMatch == countOfMatch)
        }
    },
    FIFTH(3, 5_000) {
        override fun isMatching(
            countOfMatch: Int,
            matchBonus: Boolean,
        ): Boolean {
            return (this.countOfMatch == countOfMatch)
        }
    },
    MISS(0, 0) {
        override fun isMatching(
            countOfMatch: Int,
            matchBonus: Boolean,
        ): Boolean {
            return countOfMatch !in (3..6)
        }
    }, ;

    protected abstract fun isMatching(
        countOfMatch: Int,
        matchBonus: Boolean,
    ): Boolean

    companion object {
        fun valueOf(
            countOfMatch: Int,
            matchBonus: Boolean,
        ): Rank1 {
            return entries.find {
                it.isMatching(countOfMatch, matchBonus)
            } ?: throw IllegalArgumentException()
        }
    }
}

fun main() {
}
