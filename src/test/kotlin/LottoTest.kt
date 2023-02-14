import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또 번호는 1 이상 45 이하가 아닌 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(listOf(1, 2, 3, 4, 5, 50))
        }
    }

    @Test
    fun `로또 번호는 1 이상 45 이하인 경우`() {
        assertDoesNotThrow {
            Lotto(listOf(1, 45, 10, 11, 12, 13))
        }
    }

    @Test
    fun `로또 번호는 6개가 아닌 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(listOf(1, 2, 3, 4, 5, 6, 7))
        }
    }

    @Test
    fun `로또 번호가 6개인 경우`() {
        assertDoesNotThrow {
            Lotto(listOf(1, 45, 10, 11, 12, 13))
        }
    }
}
