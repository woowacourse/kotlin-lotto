package domain.lotto

import domain.lotto.generator.LottoVendingMachine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoVendingMachineTest {

    @Test
    fun `로또 개수만큼 발행`() {
        // given
        val lottoCount: Int = 5
        val lotto = Lotto(
            setOf(
                LottoNumber.of(1),
                LottoNumber.of(2),
                LottoNumber.of(3),
                LottoNumber.of(4),
                LottoNumber.of(5),
                LottoNumber.of(6),
            ),
        )
        // when
        val lottoBundle = LottoVendingMachine.getLottoBundle(lottoCount) { lotto }

        // then
        assertThat(lottoBundle.lottos.size).isEqualTo(lottoCount)
    }
}
