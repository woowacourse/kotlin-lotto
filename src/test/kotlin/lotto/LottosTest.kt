package lotto

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.Lottos
import lotto.model.Rank
import lotto.model.WinningLotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottosTest {
    @Test
    fun `당첨 번호에 따라 구매한 로또들의 등수를 찾아 로또 결과를 반환한다`() {
        // given
        val lotto1 = Lotto(1, 2, 3, 4, 5, 6) // 1등
        val lotto2 = Lotto(1, 2, 3, 4, 5, 7) // 2등
        val lotto3 = Lotto(1, 2, 3, 4, 5, 8) // 3등
        val lotto4 = Lotto(10, 11, 12, 13, 14, 15) // miss

        val lottos = Lottos(listOf(lotto1, lotto2, lotto3, lotto4))

        val winningLotto =
            WinningLotto(
                Lotto(1, 2, 3, 4, 5, 6),
                bonusNumber = LottoNumber(7),
            )

        // when
        val result = lottos.calculateLottoResult(winningLotto)

        // then
        val expectedRanks =
            mapOf(
                Rank.FIRST to 1,
                Rank.SECOND to 1,
                Rank.THIRD to 1,
                Rank.MISS to 1,
            )
        assertThat(result.ranks).isEqualTo(expectedRanks)
    }
}
