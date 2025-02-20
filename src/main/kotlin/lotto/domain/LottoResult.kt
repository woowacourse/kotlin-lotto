package lotto.domain

enum class LottoResult(
    val prizeAmount: Int,
    val bonusMatched: BonusMatched,
    val matchCount: Int,
) {
    FAIL(0, BonusMatched.IRRELEVANT, 0),
    FIFTH(5_000, BonusMatched.IRRELEVANT, 3),
    FOURTH(50_000, BonusMatched.IRRELEVANT, 4),
    THIRD(1_500_000, BonusMatched.NO, 5),
    SECOND(30_000_000, BonusMatched.YES, 5),
    FIRST(2_000_000_000, BonusMatched.IRRELEVANT, 6),
    ;

    enum class BonusMatched {
        YES,
        NO,
        IRRELEVANT,
    }

    companion object {
        fun from(
            wonLotto: WinningLotto,
            boughtLotto: Lotto,
        ): LottoResult {
            if (isFailLotto(wonLotto, boughtLotto)) return FAIL

            val sameMatchCountResults: List<LottoResult> = findSameMatchCountResults(wonLotto, boughtLotto)
            if (sameMatchCountResults.size == 1) return sameMatchCountResults.first()

            if (bonusMatched(wonLotto, boughtLotto)) {
                return sameMatchCountResults.find { entry: LottoResult -> entry.bonusMatched == BonusMatched.YES }
                    ?: throw IllegalStateException(ERROR_MESSAGE_NO_SUCH_LOTTO_RESULT)
            }
            return sameMatchCountResults.find { entry: LottoResult -> entry.bonusMatched == BonusMatched.NO }
                ?: throw IllegalStateException(ERROR_MESSAGE_NO_SUCH_LOTTO_RESULT)
        }

        private fun calculateMatchCount(
            wonLotto: WinningLotto,
            boughtLotto: Lotto,
        ): Int = boughtLotto.numbers.count { number: Int -> number in wonLotto.numbers }

        private fun isFailLotto(
            wonLotto: WinningLotto,
            boughtLotto: Lotto,
        ): Boolean = calculateMatchCount(wonLotto, boughtLotto) < entries.minus(FAIL).minOf { it.matchCount }

        private fun findSameMatchCountResults(
            wonLotto: WinningLotto,
            boughtLotto: Lotto,
        ): List<LottoResult> =
            entries.filter { entry: LottoResult ->
                entry.matchCount ==
                    calculateMatchCount(
                        wonLotto,
                        boughtLotto,
                    )
            }

        private fun bonusMatched(
            wonLotto: WinningLotto,
            boughtLotto: Lotto,
        ): Boolean = boughtLotto.numbers.contains(wonLotto.bonusNumber)

        private const val ERROR_MESSAGE_NO_SUCH_LOTTO_RESULT = "로또 비교에 실패했습니다."
    }
}
