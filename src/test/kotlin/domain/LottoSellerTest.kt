package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoSellerTest {
    @Test
    fun `입력받은 개수만큼 로또를 발급한다`() {
        // given
        val lottoMachine = RandomLottoMachine()
        val lottoSeller = LottoSeller(lottoMachine)

        // when
        val actual = lottoSeller.sellLottos(2)

        // then
        assertThat(actual.size).isEqualTo(2)
    }
}
