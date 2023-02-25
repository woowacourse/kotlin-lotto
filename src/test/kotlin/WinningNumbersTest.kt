import domain.model.LottoResult
import domain.model.WinningNumbers
import domain.model.lotto.Lotto
import domain.model.lotto.LottoNumber
import domain.model.lotto.LottoTicket
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
    fun `번호가 6개 일치하면 당첨 결과는 1등이다`() {
        val winningNumber = generateWinningNumbers(listOf(1, 2, 3, 4, 5, 6), 7)
        val lotto = Lotto.create(LottoTicket(listOf(1, 2, 3, 4, 5, 6)))
        assertThat(winningNumber.findLottoResult(lotto.numbers)).isEqualTo(LottoResult.FIRST)
    }

    @Test
    fun `번호가 5개 일치하고 보너스 번호가 일치하면 당첨 결과는 2등이다`() {
        val winningNumber = generateWinningNumbers(listOf(1, 2, 3, 4, 5, 6), 7)
        val lotto = Lotto.create(LottoTicket(listOf(1, 2, 3, 4, 5, 7)))
        assertThat(winningNumber.findLottoResult(lotto.numbers)).isEqualTo(LottoResult.SECOND)
    }

    @Test
    fun `번호가 5개 일치하고 보너스 번호가 일치하지 않으면 당첨 결과는 3등이다`() {
        val winningNumber = generateWinningNumbers(listOf(1, 2, 3, 4, 5, 6), 7)
        val lotto = Lotto.create(LottoTicket(listOf(1, 2, 3, 4, 5, 8)))
        assertThat(winningNumber.findLottoResult(lotto.numbers)).isEqualTo(LottoResult.THIRD)
    }

    @Test
    fun `번호가 4개 일치하면 당첨 결과는 4등이다`() {
        val winningNumber = generateWinningNumbers(listOf(1, 2, 3, 4, 5, 6), 7)
        val lotto = Lotto.create(LottoTicket(listOf(1, 2, 3, 4, 8, 9)))
        assertThat(winningNumber.findLottoResult(lotto.numbers)).isEqualTo(LottoResult.FORTH)
    }

    @Test
    fun `번호가 3개 일치하면 당첨 결과는 5등이다`() {
        val winningNumber = generateWinningNumbers(listOf(1, 2, 3, 4, 5, 6), 7)
        val lotto = Lotto.create(LottoTicket(listOf(1, 2, 3, 10, 11, 12)))
        assertThat(winningNumber.findLottoResult(lotto.numbers)).isEqualTo(LottoResult.FIFTH)
    }

    @Test
    fun `번호가 2개 이하로 일치하면 당첨되지 않는다`() {
        val winningNumber = generateWinningNumbers(listOf(1, 2, 3, 4, 5, 6), 7)
        val lotto = Lotto.create(LottoTicket(listOf(1, 2, 10, 11, 12, 13)))
        assertThat(winningNumber.findLottoResult(lotto.numbers)).isEqualTo(LottoResult.MISS)
    }
}
