import domain.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MoneyTest {

    @Test
    fun `입력받은 금액이 money로 생성되는 지 확인`() {
        val money = Money(1000)
        assertThat(money.price).isEqualTo(1000)
    }

    @Test
    fun `입력된 금액이 음수인 경우 예외처리 확인`() {
        assertThrows<IllegalArgumentException> {
            Money(-9000)
        }
    }

    @Test
    fun `입력된 금액이 1000으로 안나눠지는 경우 예외처리 확인`() {
        assertThrows<IllegalArgumentException> {
            Money(925)
        }
    }
}
