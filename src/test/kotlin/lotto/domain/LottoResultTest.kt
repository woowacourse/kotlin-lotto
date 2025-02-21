package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoResultTest {
    @Test
    fun `지난 주 당첨 번호를 참고하여 구매한 로또의 결과를 계산한다`() {
        val wonLotto =
            WinningLotto(Lotto(1, 2, 3, 4, 5, 6), bonusNumber = LottoNumber(7))

        val expectedFirstLotto = Lotto(1, 2, 3, 4, 5, 6)
        val expectedSecondLotto = Lotto(1, 2, 3, 4, 5, 7)
        val expectedThirdLotto = Lotto(1, 2, 3, 4, 5, 45)
        val expectedFourthLotto = Lotto(1, 2, 3, 4, 44, 45)
        val expectedFifthLotto = Lotto(1, 2, 3, 43, 44, 45)
        val expectedFailLotto = Lotto(1, 2, 42, 43, 44, 45)

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
