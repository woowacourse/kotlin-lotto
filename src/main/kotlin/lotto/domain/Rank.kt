package lotto.domain

enum class Rank(val countOfSameNumber: Int, val prize: Int) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 1_500_000),
    THIRD(4, 50_000),
    FOURTH(3, 5000),
    NONE(0, 0);

    companion object {
        fun of(countOfSameNumber: Int): Rank {
            return values().filter { it.countOfSameNumber == countOfSameNumber}
                .getOrElse<Rank>(0) { NONE }
        }
    }
}
