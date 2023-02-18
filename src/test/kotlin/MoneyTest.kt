import domain.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MoneyTest {

    @Test
    fun `입력받은 금액 확인`() {
        val money = Money(1000)
        assertThat(money.price).isEqualTo(1000)
    }

    @Test
    fun `입력된 금액이 음수인 경우`() {
        assertThrows<IllegalArgumentException> {
            Money(-9000)
        }
    }

    @Test
    fun `입력된 금액이 1000으로 안나눠지는 경우`() {
        assertThrows<IllegalArgumentException> {
            Money(925)
        }
    }

    @ParameterizedTest
    @CsvSource(value = ["10000,10", "12000,12", "50000,50"])
    fun `입력된 금액으로 로또를 몇 개 살 수 있는지 확인`(money: Int, count: Int) {
        val money = Money(money)
        val actual = money.getLottoCount()

        assertThat(actual).isEqualTo(count)
    }
}
