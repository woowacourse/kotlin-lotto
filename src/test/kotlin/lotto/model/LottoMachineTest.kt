package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest {
    @Test
    fun `입력된 금액에 맞게 로또를 발행한다`() {
        val amount = Amount(10000)
        val lottoMachine = LottoMachine(amount)
        val lottoTickets = lottoMachine.publishLottoTickets()
        assertThat(lottoTickets.size).isEqualTo(amount.getLottoQuantity())
    }
}
