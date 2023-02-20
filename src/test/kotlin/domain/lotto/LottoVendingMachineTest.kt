package domain.lotto

import domain.lotto.generator.LottoVendingMachine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoVendingMachineTest {

    @Test
    fun `로또 개수만큼 발행`() {
        // given
        val lottoCount: Int = 5
        val lotto = Lotto(1, 2, 3, 4, 5, 6)
        // when
        val lottoBundle = LottoVendingMachine.getLottoBundle(lottoCount) { lotto }

        // then
        assertThat(lottoBundle.lottos.size).isEqualTo(lottoCount)
    }
}
