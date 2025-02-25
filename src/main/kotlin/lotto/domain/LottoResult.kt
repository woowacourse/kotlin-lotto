package lotto.domain

enum class LottoResult(
    val prizeAmount: Int,
    val bonusMatched: BonusMatched,
    val matchCount: Int,
) {
    FIRST(2_000_000_000, BonusMatched.IRRELEVANT, 6),
    SECOND(30_000_000, BonusMatched.YES, 5),
    THIRD(1_500_000, BonusMatched.NO, 5),
    FOURTH(50_000, BonusMatched.IRRELEVANT, 4),
    FIFTH(5_000, BonusMatched.IRRELEVANT, 3),
    FAIL(0, BonusMatched.IRRELEVANT, 0),
    ;

    enum class BonusMatched {
        YES,
        NO,
        IRRELEVANT,
    }

    companion object {
        fun from(
            winLotto: WinLotto,
            boughtLotto: Lotto,
        ): LottoResult =
            entries
                .filterNot { result: LottoResult ->
                    val bonusMatched: Boolean = winLotto.bonusNumberMatched(boughtLotto)
                    if (bonusMatched) {
                        result.bonusMatched == BonusMatched.NO
                    } else {
                        result.bonusMatched == BonusMatched.YES
                    }
                }.calculateHighestPrize(winLotto, boughtLotto)

        private fun List<LottoResult>.calculateHighestPrize(
            winLotto: WinLotto,
            boughtLotto: Lotto,
        ): LottoResult {
            val matchCount = boughtLotto.countMatch(winLotto)
            return filter { result: LottoResult -> result.matchCount <= matchCount }
                .maxBy { result: LottoResult -> result.prizeAmount }
        }
    }
}
