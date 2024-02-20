import model.Price
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PriceTest {
    @Test
    fun `Price는 음수일 수 없다`() {
        assertThrows<IllegalArgumentException> {
            Price(-1)
        }
    }
}
