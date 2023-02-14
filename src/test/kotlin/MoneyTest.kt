import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MoneyTest {

    @Test
    fun `입력받은 금액 확인`() {
        val money = Money(1000)
        assertThat(money.price).isEqualTo(1000)
    }

}