package lotto.domain

enum class LottoResult(
    val prizeAmount: Int,
) {
    FIRST(2_000_000_000),
    SECOND(30_000_000),
    THIRD(1_500_000),
    FOURTH(50_000),
    FIFTH(5_000),
    FAIL(0),
    ;

    companion object {
        fun from(
            wonLotto: WinningLotto,
            boughtLotto: Lotto,
        ): LottoResult {
            val matchCount = boughtLotto.numbers.count { number: Int -> number in wonLotto.numbers }
            val bonusMatched = boughtLotto.numbers.contains(wonLotto.bonusNumber)

            return when {
                matchCount == 6 -> FIRST
                matchCount == 5 && bonusMatched -> SECOND
                matchCount == 5 && !bonusMatched -> THIRD
                matchCount == 4 -> FOURTH
                matchCount == 3 -> FIFTH
                matchCount in 0..2 -> FAIL
                else -> throw IllegalStateException(ERROR_MESSAGE_NO_SUCH_LOTTO_RESULT)
            }
        }

        private const val ERROR_MESSAGE_NO_SUCH_LOTTO_RESULT = "로또 비교에 실패했습니다."
    }
}
