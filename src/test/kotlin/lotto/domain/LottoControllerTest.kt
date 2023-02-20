package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoControllerTest {

    @Test
    fun `구매한 장 수 만큼 로또를 발급한다`() {
        assertThat(LottoController().getUserLotto(5).lotto.size).isEqualTo(5)
    }
}
