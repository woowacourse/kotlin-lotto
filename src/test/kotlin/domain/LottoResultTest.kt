package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoResultTest {
    @Test
    fun `로또 결과를 정상적으로 저장할 수 있다`() {
        val lottos = listOf(
            Lotto.create(listOf(1, 2, 3, 4, 5, 6)), // 1등
            Lotto.create(listOf(2, 3, 4, 5, 6, 7)), // 2등
            Lotto.create(listOf(3, 4, 5, 6, 7, 8)), // 4등
        )
        val winningLotto = WinningLotto(listOf(1, 2, 3, 4, 5, 6), 7)
        val result = LottoResult.of(lottos, winningLotto)

        val expect = LottoResult(
            mapOf(
                Rank.FIRST to 1,
                Rank.SECOND to 1,
                Rank.THIRD to 0,
                Rank.FOURTH to 1,
                Rank.FIFTH to 0,
                Rank.MISS to 0,
            ),
        )

        assertThat(result).isEqualTo(expect)
    }
}
