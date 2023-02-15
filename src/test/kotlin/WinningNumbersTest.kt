import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class WinningNumbersTest {

    @Test
    fun `당첨 번호가 1이상 45이하가 아닌 경우`() {
        assertThrows<IllegalArgumentException> {
            WinningNumbers(
                setOf(1, 2, 0, 3, 46, 5),
                3
            )
        }
    }

    @Test
    fun `당첨 번호가 1이상 45이하인 경우`() {
        assertDoesNotThrow {
            WinningNumbers(
                setOf(1, 2, 3, 4, 5, 6),
                7
            )
        }
    }

    @Test
    fun `당첨 번호가 6개가 아닌 경우`() {
        assertThrows<IllegalArgumentException> {
            WinningNumbers(
                setOf(1, 2, 3, 4, 5, 6, 7),
                8
            )
        }
    }

    @Test
    fun `당첨 번호가 6개인 경우`() {
        assertDoesNotThrow {
            WinningNumbers(
                setOf(1, 2, 3, 4, 5, 6),
                7
            )
        }
    }

    @Test
    fun `보너스 번호가 당첨 번호와 중복된 경우`() {
        assertThrows<IllegalArgumentException> {
            WinningNumbers(
                setOf(1, 2, 3, 4, 5, 6),
                3
            )
        }
    }

    @Test
    fun `보너스 번호가 당첨 번호와 중복되지 않은 경우`() {
        assertDoesNotThrow {
            WinningNumbers(
                setOf(1, 2, 3, 4, 5, 6),
                7
            )
        }
    }
}
