package lotto.domain

enum class Result(
    val prize: Int,
    val matchCount: Int,
    val requireBonus: Boolean,
) {
    FAIL(0, 0, false),
    FIFTH(5_000, 3, false),
    FOURTH(50_000, 4, false),
    THIRD(1_500_000, 5, false),
    SECOND(30_000_000, 5, true),
    FIRST(2_000_000_000, 6, false),
    ;

    companion object {
        fun from(
            winningLotto: WinningLotto,
            userLotto: Lotto,
        ): Result {
            val matchCount = calculateMatchCount(winningLotto, userLotto)
            val hasBonus = bonusMatched(winningLotto, userLotto)
            val result: Result =
                Result.entries.sortedWith(compareBy(Result::matchCount, Result::requireBonus)).reversed().find { result: Result ->
                    matchCount == result.matchCount && hasBonus >= result.requireBonus
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
