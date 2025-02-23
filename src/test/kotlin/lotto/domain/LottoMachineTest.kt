package lotto.domain

import lotto.generator.LottoRandomGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoMachineTest {
    private val lottoMachine = LottoMachine()

    @Test
    fun `로또 1장의 가격은 1000원이다`() {
        assertThat(lottoMachine.price).isEqualTo(1_000)
    }

    @ValueSource(ints = [1000, 3500, 14000, 50000])
    @ParameterizedTest
    fun `로또 머신에 돈을 넣으면 로또가 발행된다`(input: Int) {
        assertThat(lottoMachine.buyLottoTickets(LottoPayment(input), LottoRandomGenerator())).hasSize(input / 1000)
    }
}
