package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest {
    @Test
    fun `입력된 금액에 맞게 로또를 발행한다`() {
        // Given
        val money = 10000
        val amount = LottoPurchaseAmount(money)
        val lottoMachine = LottoMachine()

        // When
        val lottoTickets = lottoMachine.publishLottoTickets(amount.getLottoQuantity())

        // Then
        assertThat(lottoTickets.size).isEqualTo(amount.getLottoQuantity())
    }
}
