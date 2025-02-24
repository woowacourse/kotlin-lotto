package lotto.domain

import lotto.domain.model.Lotto
import lotto.domain.model.Lottos
import lotto.domain.model.WinningLotto
import lotto.domain.value.EarningInfo
import lotto.domain.value.LottoNumber
import lotto.enums.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LottoWinningStatsTest {
    private lateinit var winningLotto: WinningLotto
    private lateinit var lottos: Lottos

    @BeforeEach
    fun setUp() {
        winningLotto = WinningLotto(Lotto.createManual((1..6).toSet()), LottoNumber(45))
        lottos = Lottos(listOf(Lotto.createRandom()))
    }

    @Test
    fun `당첨 수익률 객체를 반환한다`() {
        // Given
        val winningStats = lottos.getLottoWinningStats(winningLotto)

        // When
        val earningRate = winningStats.getEarningRate()

        // Then
        assertThat(earningRate).isExactlyInstanceOf(EarningInfo::class.java)
    }

    @Test
    fun `MISS를 제외한 전체 당첨 통계 정보를 반환한다`() {
        // Given
        val winningStats = lottos.getLottoWinningStats(winningLotto)

        // When
        val winningStatsWithEmpty = winningStats.getWinningStatsWithEmptyWithoutMiss()

        // Then
        assertThat(winningStatsWithEmpty.size).isEqualTo(Rank.entries.size - 1)
    }
}
