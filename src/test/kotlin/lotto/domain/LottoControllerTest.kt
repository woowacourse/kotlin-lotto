package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoControllerTest {

    @Test
    fun `금액을 통해 몇 장의 로또가 발급되는지 구한다`() {
        val money = 14000
        assertThat(LottoController().getNumberOfLotto(money)).isEqualTo(14)
    }

    @Test
    fun `구매한 장 수 만큼 로또를 발급한다`() {
        assertThat(LottoController().generateLotto(5).size).isEqualTo(5)
    }

    @Test
    fun `로또 가격으로 나누어 떨어지지 않는 경우 false를 반환한다`() {
        assertThat(
            LottoController().isDivided(14100)
        ).isFalse
    }
}
