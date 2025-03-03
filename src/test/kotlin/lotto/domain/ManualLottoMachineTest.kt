package lotto.domain

import io.kotest.matchers.shouldBe
import lotto.domain.model.Amount
import lotto.domain.model.LottoPurchaseInfo
import org.junit.jupiter.api.Test

class ManualLottoMachineTest {
    @Test
    fun `번호를 입력하면 로또를 발행한다`() {
        // Given
        val lottoPurchaseAmount = LottoPurchaseInfo(Amount(2000), 2)
        val manualLottoMachine = ManualLottoMachine(listOf(listOf(1, 2, 3, 4, 5, 6), listOf(7, 8, 9, 10, 11, 12)))

        // When
        val manualLottoTickets = manualLottoMachine.publishLottoTickets(lottoPurchaseAmount)

        // Then
        manualLottoTickets.size shouldBe 2
    }
}
