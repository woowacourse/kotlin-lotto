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
            wonLotto: WinningLotto,
            boughtLotto: Lotto,
        ): LottoResult {
            if (bonusMatched(wonLotto.bonusNumber, boughtLotto)) {
                return entries
                    .filterNot { result: LottoResult -> result.bonusMatched == BonusMatched.NO }
                    .calculateHighestPrize(wonLotto, boughtLotto)
            }

            return entries
                .filterNot { result: LottoResult -> result.bonusMatched == BonusMatched.YES }
                .calculateHighestPrize(wonLotto, boughtLotto)
        }

        private fun bonusMatched(
            bonusNumber: LottoNumber,
            boughtLotto: Lotto,
        ): Boolean = boughtLotto.containLottoNumber(bonusNumber)

        private fun List<LottoResult>.calculateHighestPrize(
            wonLotto: WinningLotto,
            boughtLotto: Lotto,
        ): LottoResult =
            filter { result: LottoResult -> result.matchCount <= calculateMatchCount(wonLotto, boughtLotto) }
                .maxBy { result: LottoResult -> result.prizeAmount }

        private fun calculateMatchCount(
            wonLotto: WinningLotto,
            boughtLotto: Lotto,
        ): Int = boughtLotto.numbers.count { lottoNumber: LottoNumber -> lottoNumber.value in wonLotto.numbers }
    }
}
