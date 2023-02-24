package domain

import domain.lotto.Lotto
import domain.lotto.LottoBundle
import domain.lotto.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinStatisticsTest {
    private val lottoBundle = LottoBundle(
        listOf(
            Lotto(1, 2, 3, 4, 5, 6),
            Lotto(1, 2, 3, 4, 5, 6),
            Lotto(1, 2, 3, 4, 5, 7),
            Lotto(1, 2, 3, 4, 5, 8),
            Lotto(1, 2, 3, 4, 5, 8),
        ),
    )
    private val winningNumbers = WinningNumbers(Lotto(1, 2, 3, 4, 5, 6), LottoNumber.of(7))

    @Test
    fun `객체가 생성될 때 rankCount가 잘 계산되는지 테스트`() {
        // given
        val expected: Map<Rank, Int> = mapOf(
            Rank.FIRST to 2,
            Rank.SECOND to 1,
            Rank.THIRD to 2,
        )

        // when
        val winStatistics = WinStatistics(winningNumbers, lottoBundle)

        // then
        assertThat(winStatistics.rankCount).isEqualTo(expected)
    }

    @Test
    fun `당첨 통계와 사용한 돈에 따라 수익률을 계산한다`() {
        // given
        val expected: Double = 806600.0

        // when
        val winStatistics: WinStatistics = WinStatistics(winningNumbers, lottoBundle) // 당첨 상금: 4033000000

        // then
        assertThat(winStatistics.earningRate).isEqualTo(expected)
    }
}
