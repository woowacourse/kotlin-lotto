import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MoneyTest {

    @Test
    fun `입력받은 금액 확인`() {
        val money = Money(1000)
        assertThat(money.price).isEqualTo(1000)
    }

    @Test
    fun `입력된 금액이 null 일 때`(){
        assertThrows<IllegalArgumentException> {
            Money(null)
        }
    }

}