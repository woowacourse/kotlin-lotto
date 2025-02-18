import model.Lotto
import model.LottoNumber
import model.WinningLotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class WinningLottoTest {
    @Test
    fun `당첨 번호와 보너스 번호는 중복되지 않는다`() {
        assertDoesNotThrow {
            val lottoNumbers = (1..6).map { LottoNumber(it) }
            val winningNumbers = Lotto(lottoNumbers)
            val bonusNumber = LottoNumber(45)
            WinningLotto(winningNumbers, bonusNumber)
        }
    }

    @Test
    fun `당첨 번호와 보너스 번호가 중복되면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            val lottoNumbers = (1..6).map { LottoNumber(it) }
            val winningNumbers = Lotto(lottoNumbers)
            val bonusNumber = LottoNumber(1)
            WinningLotto(winningNumbers, bonusNumber)
        }
    }

    @Test
    fun `당첨 로또 번호와 일치하는 번호의 개수를 구한다`() {
        val lottoNumbers = (1..6).map { LottoNumber(it) }
        val winningNumbers = Lotto(lottoNumbers)
        val bonusNumber = LottoNumber(45)
        val winningLotto = WinningLotto(winningNumbers, bonusNumber)
        val lotto = Lotto(lottoNumbers)
        assertThat(winningLotto.getCountOfMatch(lotto)).isEqualTo(6)
    }
}
