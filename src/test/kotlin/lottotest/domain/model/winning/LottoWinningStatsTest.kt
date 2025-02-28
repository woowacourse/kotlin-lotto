package lottotest.domain.model.winning

import lotto.domain.enums.Rank
import lotto.domain.model.Lotto
import lotto.domain.model.Lottos
import lotto.domain.model.winning.WinningLotto
import lotto.domain.valueobject.EarningInfo
import lotto.domain.valueobject.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LottoWinningStatsTest {
    private lateinit var winningLotto: WinningLotto
    private lateinit var lottosOnlyFirstRank: Lottos

    @BeforeEach
    fun setUp() {
        val sampleLottoTicket = listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }
        val sampleLotto = Lotto(sampleLottoTicket)

        winningLotto =
            WinningLotto(
                sampleLotto,
                LottoNumber(45),
            )
        lottosOnlyFirstRank = Lottos(listOf(sampleLotto))
    }

    @Test
    fun `인스턴스가 보유한 통계 정보를 바탕으로 당첨 수익률 객체를 반환한다`() {
        // Given
        val winningStats = lottosOnlyFirstRank.getLottoWinningStats(winningLotto)

        // When
        val earningRate = winningStats.getEarningInfo()

        // Then
        assertThat(earningRate).isExactlyInstanceOf(EarningInfo::class.java)
    }

    @Test
    fun `MISS를 제외한 전체 당첨 통계 정보를 담은 Map을 반환한다`() {
        // Given
        val winningStats = lottosOnlyFirstRank.getLottoWinningStats(winningLotto)

        // When
        val winningStatsWithEmpty = winningStats.getWholeWinningStatsWithoutMiss()

        // Then
        assertThat(winningStatsWithEmpty.size).isEqualTo(Rank.entries.size - 1)
    }
}
