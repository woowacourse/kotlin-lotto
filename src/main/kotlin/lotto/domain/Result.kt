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
            winningLotto: WinningLotto,
            userLotto: Lotto,
        ): Result {
            val matchCount = calculateMatchCount(winningLotto, userLotto)
            val hasBonus = bonusMatched(winningLotto, userLotto)
            val result: Result =
                Result.entries.find { result: Result ->
                    matchCount == result.matchCount && hasBonus == result.requireBonus
                } ?: Result.entries.find { result: Result ->
                    matchCount == result.matchCount
                } ?: FAIL
            return result
        }

        private fun calculateMatchCount(
            winningLotto: WinningLotto,
            userLotto: Lotto,
        ): Int =
            userLotto.numbers.count { lottoNumber: LottoNumber ->
                lottoNumber in winningLotto.lotto.numbers
            }

        private fun bonusMatched(
            winningLotto: WinningLotto,
            userLotto: Lotto,
        ): Boolean = userLotto.numbers.contains(winningLotto.bonusNumber)
    }
}
