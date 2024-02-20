import model.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MoneyTest {
    @Test
    fun `Money는 음수일 수 없다`() {
        assertThrows<IllegalArgumentException> {
            Money(-1)
        }
    }

    @Test
    fun `Money를 Money로 나누면 정수 몫이 나온다`() {
        val count = Money(10) / Money(10)
        assertThat(count).isEqualTo(1)
    }
}
