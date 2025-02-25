package lotto.domain

enum class Result(
    val prize: Int,
    val requireBonus: Boolean,
    val matchCount: Int,
) {
    FAIL(0, false, 0),
    FIFTH(5_000, false, 3),
    FOURTH(50_000, false, 4),
    THIRD(1_500_000, false, 5),
    SECOND(30_000_000, true, 5),
    FIRST(2_000_000_000, false, 6),
    ;

    companion object {
        fun from(
            wonLotto: WinningLotto,
            boughtLotto: Lotto,
        ): Result {
            val matchCount = calculateMatchCount(wonLotto, boughtLotto)
            val hasBonus = bonusMatched(wonLotto, boughtLotto)
            val result: Result =
                Result.entries.find { result: Result ->
                    matchCount == result.matchCount && hasBonus == result.requireBonus
                } ?: Result.entries.find { result: Result ->
                    matchCount == result.matchCount
                } ?: FAIL
            return result
        }

        private fun calculateMatchCount(
            wonLotto: WinningLotto,
            boughtLotto: Lotto,
        ): Int =
            boughtLotto.numbers.count { lottoNumber: LottoNumber ->
                lottoNumber.value in wonLotto.numbers
            }

        private fun bonusMatched(
            wonLotto: WinningLotto,
            boughtLotto: Lotto,
        ): Boolean = boughtLotto.numbers.contains(wonLotto.bonusNumber)
    }
}
