package domain

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = [-1, 100001])
    fun `돈을 생성할 때 금액이 0 이상 100000 이하가 아니면 에러가 발생한다`(amount: Int) {
        assertThatIllegalArgumentException().isThrownBy { Money(amount) }
            .withMessage("돈은 0원 이상 100000원 이하의 값으로만 존재할 수 있습니다.")
    }
    @ParameterizedTest
    @ValueSource(ints = [0, 100000])
    fun `0 이상 100000 이하의 금액으로 돈을 생성할 수 있다`(amount: Int) {
        assertDoesNotThrow { Money(amount) }
    }

    @Test
    fun `돈을 다른 돈으로 나누면 금액을 나눈 값을 반환한다`() {
        val money1 = Money(10000)
        val money2 = Money(1000)

        val result = money1 / money2

        assertThat(result).isEqualTo(10)
    }

    @Test
    fun `돈을 개수로 곱하면 금액을 개수로 곱한 금액의 돈을 반환한다`() {
        val money = Money(10000)

        val result = money * Count(10)

        assertThat(result).isEqualTo(Money(100000))
    }

    @Test
    fun `돈을 더 작거나 같은 금액의 돈으로 빼면 금액의 차이만큼의 돈을 반환한다`() {
        val money1 = Money(10000)
        val money2 = Money(1000)

        val result = money1 - money2

        assertThat(result).isEqualTo(Money(9000))
    }

    @Test
    fun `돈을 비교할 때 금액에 따라 비교한다`() {
        val money1 = Money(10000)
        val money2 = Money(9999)

        assertThat(money1 > money2).isTrue
    }
}
