import domain.model.Lotto
import domain.model.WinningLotto
import domain.service.LottoCalculator
import domain.value.LottoNumber
import enums.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LottoCalculatorTest {
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
        val lottoCalculator = LottoCalculator(winningLotto, lottos)
        val winningStats = lottoCalculator.calculateWinningStats()

        assertThat(winningStats.winningStats[Rank.FIRST]).isEqualTo(1)
    }

    @Test
    fun `당첨되지 않은 로또가 1개이면 미당첨 횟수는 1이다`() {
        val firstRankNumbers = (1..6).map { LottoNumber(it) }
        val missRankNumbers = (11..16).map { LottoNumber(it) }
        val lottos = listOf(Lotto(firstRankNumbers), Lotto(missRankNumbers))
        val lottoCalculator = LottoCalculator(winningLotto, lottos)
        val winningStats = lottoCalculator.calculateWinningStats()

        assertThat(winningStats.winningStats[Rank.MISS]).isEqualTo(1)
    }
}
