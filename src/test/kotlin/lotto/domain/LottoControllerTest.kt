package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoControllerTest {

    @Test
    fun `금액을 통해 몇 장의 로또가 발급되는지 구한다`() {
        val money = 14000
        assertThat(LottoController().getNumberOfLotto(money)).isEqualTo(14)
    }
}
