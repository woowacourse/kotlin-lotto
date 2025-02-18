import model.LottoNumber
import model.WinningLotto
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class WinningLottoTest {
    @Test
    fun `6개의 당첨 번호와 보너스 번호를 갖는다`() {
        assertDoesNotThrow {
            val winningNumbers = (1..6).map { LottoNumber(it) }
            val bonusNumber = LottoNumber(1)
            WinningLotto(winningNumbers, bonusNumber)
        }
    }
}
