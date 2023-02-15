import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoGeneratorTest {

    @Test
    fun `구입 금액은 1000원 단위가 아닌 경우`() {
        assertThrows<IllegalArgumentException> {
            LottoGenerator(2500)
        }
    }

    @Test
    fun `구입 금액이 1000원인 경우`() {
        assertDoesNotThrow { LottoGenerator(3000) }
    }

    @Test
    fun `구입 금액이 음수인 경우`() {
        assertThrows<IllegalArgumentException> {
            LottoGenerator(-1000)
        }
    }
}
