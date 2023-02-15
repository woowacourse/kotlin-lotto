package lotto.domain

enum class Rank(val countOfMatch: Int, val winningMoney: Int, description: String) {
    FIRST(6, 2_000_000_000, "6개 일치"),
    SECOND(5, 30_000_000, "5개 일치, 보너스 볼 일치"),
    THIRD(5, 1_500_000, "5개 일치"),
    FOURTH(4, 50_000, "4개 일치"),
    FIFTH(3, 5_000, "3개 일치"),
    MISS(0, 0, "일치 없음");

    companion object {
        fun valueOf(countOfMatch: Int, matchBonus: Boolean): Rank {
            if (countOfMatch == 5) return decideSecondOrThird(matchBonus)
            return values().find { it.countOfMatch == countOfMatch } ?: MISS
        }

        fun calculatePrize(name: String, count: Int): Long {
            if (values().map { it.name }.contains(name)) {
                return Rank.valueOf(name).winningMoney.toLong() * count
            }
            return 0
        }

        private fun decideSecondOrThird(matchBonus: Boolean): Rank {
            if (matchBonus) return SECOND
            return THIRD
        }
    }
}
