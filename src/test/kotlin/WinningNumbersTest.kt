import domain.model.WinningNumbers
import domain.model.lotto.Lotto
import domain.model.lotto.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class WinningNumbersTest {

    private fun generateWinningNumbers(catchNumbers: List<Int>, bonusNumber: Int): WinningNumbers {
        return WinningNumbers(catchNumbers.map { LottoNumber.from(it) }.toSet(), LottoNumber.from(bonusNumber))
    }

    @Test
    fun `당첨 번호가 6개가 아닌 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            generateWinningNumbers(listOf(1, 2, 3, 4, 5, 6, 7), 8)
        }
    }

    @Test
    fun `당첨 번호가 6개인 경우 예외가 발생하지 않고 WinningNumbers가 생성된다`() {
        assertDoesNotThrow {
            generateWinningNumbers(listOf(1, 2, 3, 4, 5, 6), 7)
        }
    }

    @Test
    fun `보너스 번호가 당첨 번호와 중복된 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            generateWinningNumbers(listOf(1, 2, 3, 4, 5, 6), 3)
        }
    }

    @Test
    fun `보너스 번호가 당첨 번호와 중복되지 않은 경우 예외가 발생하지 않고 WinningNumbers가 생성된다`() {
        assertDoesNotThrow {
            generateWinningNumbers(listOf(1, 2, 3, 4, 5, 6), 7)
        }
    }

    @Test
    fun `당첨 번호와 일치하는 번호의 개수를 구한다`() {
        val winningNumber = generateWinningNumbers(listOf(1, 2, 3, 4, 5, 6), 7)
        val lotto = Lotto.create(listOf(1, 2, 3, 7, 8, 9))
        assertThat(
            winningNumber.getMatchCount(lotto.numbers)
        ).isEqualTo(3)
    }

    @Test
    fun `로또 번호가 보너스 번호를 포함한다`() {
        val winningNumber = generateWinningNumbers(listOf(1, 2, 3, 4, 5, 6), 7)
        val lotto = Lotto.create(listOf(1, 2, 3, 7, 8, 9))
        assertThat(winningNumber.checkMatchBonus(lotto.numbers)).isEqualTo(true)
    }

    @Test
    fun `로또 번호가 보너스 번호를 포함하지 않는 경우`() {
        val winningNumber = generateWinningNumbers(listOf(1, 2, 3, 4, 5, 6), 10)
        val lotto = Lotto.create(listOf(1, 2, 3, 7, 8, 9))
        assertThat(winningNumber.checkMatchBonus(lotto.numbers)).isEqualTo(false)
    }
}
