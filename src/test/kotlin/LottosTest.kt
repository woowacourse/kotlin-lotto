import model.Lotto
import model.LottoNumber
import model.Lottos
import model.WinningLotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LottosTest {
    private lateinit var winningLotto: WinningLotto

    @BeforeEach
    fun setUp() {
        val lottoNumbers = (1..6).map { LottoNumber(it) }
        val winningNumbers = Lotto(lottoNumbers)
        val bonusNumber = LottoNumber(45)
        winningLotto = WinningLotto(winningNumbers, bonusNumber)
    }

    @Test
    fun `로또 등수별 로또 개수를 구한다`() {
        val lottoNumbers = (1..6).map { LottoNumber(it) }
        val lotto = Lotto(lottoNumbers)
        val lottos = Lottos(listOf(lotto))
        assertThat(lottos.getLottoRanks(winningLotto)[Rank.FIRST]).isEqualTo(1)
    }
}
