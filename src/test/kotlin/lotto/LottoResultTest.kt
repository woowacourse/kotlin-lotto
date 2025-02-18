package lotto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

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

class LottoResultTest {
    @Test
    fun `지난 주 당첨 번호를 참고하여 구매한 로또의 결과를 계산한다`() {
        val wonLotto = WinningLotto(1, 2, 3, 4, 5, 6, bonusNumber = 7)

        val expectedFirstLotto = Lotto(1, 2, 3, 4, 5, 6)
        val expectedSecondLotto = Lotto(1, 2, 3, 4, 5, 7)
        val expectedThirdLotto = Lotto(1, 2, 3, 4, 5, 45)
        val expectedFourthLotto = Lotto(1, 2, 3, 4, 44, 45)
        val expectedFifthLotto = Lotto(1, 2, 3, 43, 44, 45)
        val expectedFailLotto = Lotto(8, 9, 10, 11, 12, 13)

        assertThat(LottoResult.from(wonLotto = wonLotto, boughtLotto = expectedFirstLotto)).isEqualTo(LottoResult.FIRST)
        assertThat(
            LottoResult.from(
                wonLotto = wonLotto,
                boughtLotto = expectedSecondLotto,
            ),
        ).isEqualTo(LottoResult.SECOND)
        assertThat(LottoResult.from(wonLotto = wonLotto, boughtLotto = expectedThirdLotto)).isEqualTo(LottoResult.THIRD)
        assertThat(
            LottoResult.from(
                wonLotto = wonLotto,
                boughtLotto = expectedFourthLotto,
            ),
        ).isEqualTo(LottoResult.FOURTH)
        assertThat(LottoResult.from(wonLotto = wonLotto, boughtLotto = expectedFifthLotto)).isEqualTo(LottoResult.FIFTH)
        assertThat(LottoResult.from(wonLotto = wonLotto, boughtLotto = expectedFailLotto)).isEqualTo(LottoResult.FAIL)
    }
}
