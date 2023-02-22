import domain.model.WinningNumbers
import domain.model.lotto.Lotto
import domain.model.lotto.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class WinningNumbersTest {
    @Test
    fun `당첨 번호가 6개가 아닌 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            WinningNumbers(
                setOf(
                    LottoNumber.from(1),
                    LottoNumber.from(2),
                    LottoNumber.from(3),
                    LottoNumber.from(4),
                    LottoNumber.from(5),
                    LottoNumber.from(6),
                    LottoNumber.from(7)
                ),
                LottoNumber.from(8)
            )
        }
    }

    @Test
    fun `당첨 번호가 6개인 경우`() {
        assertDoesNotThrow {
            WinningNumbers(
                setOf(
                    LottoNumber.from(1),
                    LottoNumber.from(2),
                    LottoNumber.from(3),
                    LottoNumber.from(4),
                    LottoNumber.from(5),
                    LottoNumber.from(6)
                ),
                LottoNumber.from(7)
            )
        }
    }

    @Test
    fun `보너스 번호가 당첨 번호와 중복된 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            WinningNumbers(
                setOf(
                    LottoNumber.from(1),
                    LottoNumber.from(2),
                    LottoNumber.from(3),
                    LottoNumber.from(4),
                    LottoNumber.from(5),
                    LottoNumber.from(6)
                ),
                LottoNumber.from(3)
            )
        }
    }

    @Test
    fun `보너스 번호가 당첨 번호와 중복되지 않은 경우`() {
        assertDoesNotThrow {
            WinningNumbers(
                setOf(
                    LottoNumber.from(1),
                    LottoNumber.from(2),
                    LottoNumber.from(3),
                    LottoNumber.from(4),
                    LottoNumber.from(5),
                    LottoNumber.from(6)
                ),
                LottoNumber.from(7)
            )
        }
    }

    @Test
    fun `당첨 번호와 일치하는 번호의 개수를 구한다`() {
        val winningNumber = WinningNumbers(
            setOf(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6)
            ),
            LottoNumber.from(7)
        )
        val lotto = Lotto.create(listOf(1, 2, 3, 7, 8, 9))
        assertThat(
            winningNumber.getMatchCount(
                lotto.numbers
            )
        ).isEqualTo(3)
    }

    @Test
    fun `로또 번호가 보너스 번호를 포함하는 경우`() {
        val winningNumber = WinningNumbers(
            setOf(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6)
            ),
            LottoNumber.from(7)
        )
        val lotto = Lotto.create(listOf(1, 2, 3, 7, 8, 9))
        assertThat(winningNumber.checkMatchBonus(lotto.numbers)).isEqualTo(true)
    }

    @Test
    fun `로또 번호가 보너스 번호를 포함하지 않는 경우`() {
        val winningNumber = WinningNumbers(
            setOf(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6)
            ),
            LottoNumber.from(10)
        )
        val lotto = Lotto.create(listOf(1, 2, 3, 7, 8, 9))
        assertThat(winningNumber.checkMatchBonus(lotto.numbers)).isEqualTo(false)
    }
}
