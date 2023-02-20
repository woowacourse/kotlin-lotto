package lotto.domain

import lotto.model.Lotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoControllerTest {

    @Test
    fun `구매한 장 수 만큼 로또를 발급한다`() {
        val manualLotto = listOf(Lotto(1, 2, 3, 4, 5, 6))
        assertThat(LottoController().getUserLotto(manualLotto, 4).lotto.size).isEqualTo(5)
    }
}
