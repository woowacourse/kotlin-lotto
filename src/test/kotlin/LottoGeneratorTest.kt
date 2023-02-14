import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoGeneratorTest {

    @Test
    fun `구입 금액은 1000원 단위가 아닌 경우`() {
        assertThrows<IllegalArgumentException> {
            LottoGenerator(2500)
        }
    }

}
