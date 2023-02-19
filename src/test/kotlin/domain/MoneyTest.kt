package domain

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MoneyTest {

    @ValueSource(ints = [1000, 2000, 5000, 100000])
    @ParameterizedTest
    fun `금액은 1000원 단위로 받는다`(receivedMoney: Int) {
        // given

        // when

        // then
        assertDoesNotThrow { Money.create(receivedMoney) }
    }

    @ValueSource(ints = [14400, 1, 555, 0])
    @ParameterizedTest
    fun `금액은 1000원 단위가 아니면 에러를 발생시킨다`(receivedMoney: Int) {
        // given

        // when

        // then
        assertThatIllegalArgumentException()
            .isThrownBy { Money.create(receivedMoney) }
            .withMessageContaining("${Money.PREFIX} $receivedMoney 받은 돈이 1000원 단위여야합니다.")
    }
}
