import model.Lotto
import model.LottoNumber
import model.WinningLotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class WinningLottoTest {
    private lateinit var winningNumbers: Lotto
    private lateinit var bonusNumber: LottoNumber

    @BeforeEach
    fun setUp() {
        val lottoNumbers = (1..6).map { LottoNumber(it) }
        winningNumbers = Lotto(lottoNumbers)
        bonusNumber = LottoNumber(45)
    }

    @Test
    fun `당첨 번호와 보너스 번호는 중복되지 않는다`() {
        assertDoesNotThrow {
            WinningLotto(winningNumbers, bonusNumber)
        }
    }

    @Test
    fun `당첨 번호와 보너스 번호가 중복되면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            val bonusNumber = LottoNumber(1)
            WinningLotto(winningNumbers, bonusNumber)
        }
    }

    @Test
    fun `로또 번호와 일치하는 번호의 개수를 구한다`() {
        val lottoNumbers = (2..7).map { LottoNumber(it) }
        val lotto = Lotto(lottoNumbers)
        val winningLotto = WinningLotto(winningNumbers, bonusNumber)
        assertThat(winningLotto.getCountOfMatch(lotto)).isEqualTo(5)
    }

    @Test
    fun `로또 번호와 보너스 번호 일치 여부를 구한다`() {
        val lottoNumbers = (40..45).map { LottoNumber(it) }
        val lotto = Lotto(lottoNumbers)
        val winningLotto = WinningLotto(winningNumbers, bonusNumber)
        assertThat(winningLotto.getMatchBonus(lotto)).isEqualTo(true)
    }
}
