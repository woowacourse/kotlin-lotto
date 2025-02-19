import domain.Lotto
import domain.LottoWinningStats
import domain.WinningLotto
import domain.value.LottoNumber
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LottoWinningStatsTest {
    private lateinit var winningLotto: WinningLotto

    @BeforeEach
    fun setUp() {
        val lottoNumbers = (1..6).map { LottoNumber(it) }
        val winningNumbers = Lotto(lottoNumbers)
        val bonusNumber = LottoNumber(45)
        winningLotto = WinningLotto(winningNumbers, bonusNumber)
    }

    @Test
    fun `로또 1등이 한번 당첨되면 1등 당첨 횟수는 1이다`() {
        val firstRankNumbers = (1..6).map { LottoNumber(it) }
        val lottos = listOf(Lotto(firstRankNumbers))
        val lottoWinningStats = LottoWinningStats(winningLotto, lottos)

        Assertions
            .assertThat(lottoWinningStats.getLottoRanks()[Rank.FIRST])
            .isEqualTo(1)
    }

    @Test
    fun `당첨되지 않은 로또가 1개이면 미당첨 횟수는 1이다`() {
        val firstRankNumbers = (1..6).map { LottoNumber(it) }
        val missRankNumbers = (11..16).map { LottoNumber(it) }
        val lottos = listOf(Lotto(firstRankNumbers), Lotto(missRankNumbers))
        val lottoWinningStats = LottoWinningStats(winningLotto, lottos)

        Assertions
            .assertThat(lottoWinningStats.getLottoRanks()[Rank.MISS])
            .isEqualTo(1)
    }
}
