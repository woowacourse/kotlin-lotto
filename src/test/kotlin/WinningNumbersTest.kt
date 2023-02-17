import domain.model.WinningNumbers
import domain.model.lotto.LottoNumber
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class WinningNumbersTest {
    @Test
    fun `당첨 번호가 6개가 아닌 경우`() {
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
    fun `보너스 번호가 당첨 번호와 중복된 경우`() {
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
}
