package domain

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import util.PREFIX

class MoneyTest {
    @ValueSource(ints = [14400, 1, 555, 0])
    @ParameterizedTest
    fun `금액은 1000원 단위여야 한다`(receivedMoney: Int) {
        // given

        // when

        // then
        assertThatIllegalArgumentException()
            .isThrownBy { Money(receivedMoney) }
            .withMessageContaining("$PREFIX 받은 돈이 1000원 단위여야합니다.")
    }
}
