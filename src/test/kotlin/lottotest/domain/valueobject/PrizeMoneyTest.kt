package lottotest.domain.valueobject

import lotto.domain.valueobject.PrizeMoney
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PrizeMoneyTest {
    @ParameterizedTest
    @ValueSource(longs = [-1, -1000])
    fun `당첨 금액이 음수일 수 없다`(money: Long) {
        // when then
        assertThrows<IllegalArgumentException> {
            PrizeMoney(money)
        }
    }

    @ParameterizedTest
    @ValueSource(longs = [0, 1, 123456])
    fun `당첨 금액을 입력 받으면 당첨 금액 값을 보관한다`(money: Long) {
        // when then
        assertThat(PrizeMoney(money).money).isEqualTo(money)
    }
}
