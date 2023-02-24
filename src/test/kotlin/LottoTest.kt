import domain.model.LottoResult
import domain.model.WinningNumbers
import domain.model.lotto.Lotto
import domain.model.lotto.LottoNumber
import domain.model.lotto.LottoTicket
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoTest {

    private fun generateWinningNumbers(catchNumbers: List<Int>, bonusNumber: Int): WinningNumbers {
        return WinningNumbers(catchNumbers.map { LottoNumber.from(it) }.toSet(), LottoNumber.from(bonusNumber))
    }

    @Test
    fun `로또번호가 6개 일치하는 경우, 결과는 1등이다`() {
        // given
        val lotto = Lotto.create(LottoTicket(listOf(1, 2, 3, 4, 5, 6)))

        val winningNumbers = generateWinningNumbers(
            catchNumbers = listOf(1, 2, 3, 4, 5, 6),
            bonusNumber = 7
        )
        // when
        val actual = lotto.getLottoResult(winningNumbers)
        // then
        assertThat(actual).isEqualTo(LottoResult.FIRST)
    }

    @Test
    fun `로또번호가 5개와 보너스 번호가 일치하는 경우, 결과는 2등이다`() {
        // given
        val lotto = Lotto.create(LottoTicket(listOf(1, 2, 3, 4, 5, 6)))
        val winningNumbers = generateWinningNumbers(
            catchNumbers = listOf(1, 2, 3, 4, 5, 10),
            bonusNumber = 6
        )
        // when
        val actual = lotto.getLottoResult(winningNumbers)
        // then
        assertThat(actual).isEqualTo(LottoResult.SECOND)
    }

    @Test
    fun `로또번호가 5개가 일치하고 보너스 번호가 일치하지 않는 경우, 결과는 3등이다`() {
        // given
        val lotto = Lotto.create(LottoTicket(listOf(1, 2, 3, 4, 5, 6)))
        val winningNumbers = generateWinningNumbers(
            catchNumbers = listOf(1, 2, 3, 4, 5, 10),
            bonusNumber = 9
        )
        // when
        val actual = lotto.getLottoResult(winningNumbers)
        // then
        assertThat(actual).isEqualTo(LottoResult.THIRD)
    }

    @Test
    fun `로또번호가 2개 이하 일치하는 경우, 당첨되지 않는다`() {
        // given
        val lotto = Lotto.create(LottoTicket(listOf(1, 2, 3, 4, 5, 6)))
        val winningNumbers = generateWinningNumbers(
            catchNumbers = listOf(1, 2, 8, 9, 10, 11),
            bonusNumber = 6
        )
        // when
        val actual = lotto.getLottoResult(winningNumbers)
        // then
        assertThat(actual).isEqualTo(LottoResult.MISS)
    }
}
