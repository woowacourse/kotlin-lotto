package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoResultTest {
    @Test
    fun `지난 주 당첨 번호를 참고하여 구매한 로또의 결과를 계산한다`() {
        val wonLotto =
            WinningLotto(Lotto(setOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }.toSet()), bonusNumber = LottoNumber(7))

        val expectedFirstLotto = Lotto(setOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }.toSet())
        val expectedSecondLotto = Lotto(setOf(1, 2, 3, 4, 5, 7).map { LottoNumber(it) }.toSet())
        val expectedThirdLotto = Lotto(setOf(1, 2, 3, 4, 5, 45).map { LottoNumber(it) }.toSet())
        val expectedFourthLotto = Lotto(setOf(1, 2, 3, 4, 44, 45).map { LottoNumber(it) }.toSet())
        val expectedFifthLotto = Lotto(setOf(1, 2, 3, 43, 44, 45).map { LottoNumber(it) }.toSet())
        val expectedFailLotto = Lotto(setOf(1, 2, 42, 43, 44, 45).map { LottoNumber(it) }.toSet())

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
