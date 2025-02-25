package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest {
    @Test
    fun `입력된 금액에 맞게 로또를 발행한다`() {
        // Given
        val lottoPurchaseInfo = LottoPurchaseInfo(10000, 0)
        val lottoMachine = LottoMachine()

        // When
        val lottoTickets = lottoMachine.publishLottoTickets(lottoPurchaseInfo)

        // Then
        assertThat(lottoTickets.size).isEqualTo(lottoPurchaseInfo.getTotalLottoQuantity())
    }

    @Test
    fun `사용자가 로또 번호를 입력하면 로또를 발행한다`() {
        // Given
        val lottoPurchaseInfo = LottoPurchaseInfo(1000, 1)
        val lottoMachine = LottoMachine()
        val inputNumbers = listOf(1, 2, 3, 4, 5, 6)

        // When
        val lottoTickets = lottoMachine.publishLottoTickets(lottoPurchaseInfo, inputNumbers)

        // Then
        assertThat(lottoTickets.size).isEqualTo(1)
    }
}
