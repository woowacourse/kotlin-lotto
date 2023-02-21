package domain

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MoneyTest {
    @ValueSource(ints = [1000, 100000])
    @ParameterizedTest
    fun `돈은 1000이상 100000이하만 가지고 있을 수 있다`(int: Int) {
        val money = Money(int)
        assertThat(money.value).isEqualTo(int)
    }

    @ValueSource(ints = [-1, 100001])
    @ParameterizedTest
    fun `돈은 0원 미만이거나 100001원 이상이면 에러가 발생한다`(int: Int) {
        Assertions.assertThatIllegalArgumentException()
            .isThrownBy { Money(int) }
            .withMessage("돈은 0원 이상 100000원 이하로 보유할 수 있습니다.\n잘못된 값: $int")
    }

    @Test
    fun `접근하면 값을 반환한다`() {
        val a = Money(1000)
        assertThat(a.value).isEqualTo(1000)
    }

    @Test
    fun `동등성 테스트`() {
        val a = Money(1000)
        val b = Money(1000)
        assertThat(a).isEqualTo(b)
    }
}
