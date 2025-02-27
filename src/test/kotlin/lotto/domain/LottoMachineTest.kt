package lotto.domain

import lotto.generator.LottoManualGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest {
    @Test
    fun `로또 머신은 로또를 발행한다`() {
        val input = "1,2,3,4,5,6"
        val lottoMachine = LottoMachine()
        lottoMachine.buyLottoTicket(LottoManualGenerator(input))
        val expected = listOf(Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }.toSet()))
        assertThat(lottoMachine.getLottoTickets()).isEqualTo(expected)
    }
}
