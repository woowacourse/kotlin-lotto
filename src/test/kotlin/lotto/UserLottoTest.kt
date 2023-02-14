package lotto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserLottoTest {
    @Test
    fun `각 로또마다 위의 확인 과정을 거쳐 최종 당첨 통계를 구한다`() {
        val lottos = listOf<Lotto>(
            Lotto(listOf(1, 2, 3, 4, 5, 6)),
            Lotto(listOf(1, 2, 3, 4, 5, 7)),
            Lotto(listOf(1, 2, 3, 4, 9, 10)),
            Lotto(listOf(1, 2, 3, 10, 11, 12)),
            Lotto(listOf(11, 12, 13, 14, 15, 16)),
        )
        val winningLotto = WinningLotto(listOf(1, 2, 3, 4, 5, 6), 7)
        val result = listOf(1, 1, 0, 1, 1, 1)

        assertThat(
            UserLotto(lottos).getWinningStatistics(winningLotto)
        ).isEqualTo(result)
    }
}
